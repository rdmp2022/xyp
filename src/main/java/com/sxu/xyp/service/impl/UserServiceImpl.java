package com.sxu.xyp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.common.ErrorCode;
import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.exception.BusinessException;
import com.sxu.xyp.model.domain.User;
import com.sxu.xyp.service.UserService;
import com.sxu.xyp.mapper.UserMapper;
import com.sxu.xyp.utils.FileUtils;
import net.sf.jsqlparser.util.validation.metadata.NamedObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.sxu.xyp.constant.UserConstant.*;
import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
* @author walker
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-11-09 09:42:32
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 用户注册
     * @param userAccount 账号
     * @param userPassword 密码
     * @param checkPassword 重新输入密码
     * @return 返回用户id
     */
    @Override
    public long userRegister(String userAccount, String email,String userPassword, String checkPassword) {
        //参数校验可以前端提前校验
        //不可包含特殊字符也可前端处理
//        if (StrUtil.hasBlank(userAccount, email,userPassword, checkPassword)){
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
//        }
//        if (userAccount.length() < 4){
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号长度不能少于4");
//        }
//        if (email.length() < 4){
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱长度不能少于8");
//        }
//        if (userPassword.length() < 8){
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不能少于8");
//        }
        if (!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入密码必须相同");
        }
        if (userAccount.equals(email)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号和邮箱不能相同");
        }
        //账号不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount).or().eq("email",email);
        int count = this.count(queryWrapper);
        if (count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "存在相同账号");
        }
        //加盐存数据库
        String encryptPassword = DigestUtil.md5Hex((userPassword + SALT).getBytes(StandardCharsets.UTF_8));
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setEmail(email);
        this.save(user);
        return user.getUserId();
    }

    /**
     * 用户注册
     * @param userAccount 账号
     * @param userPassword 密码
     * @return 返回<String, UserDTO>的Map型数据
     */
    @Override
    public Map<String, Object> userLogin(String userAccount, String userPassword) {
        if (StrUtil.hasBlank(userAccount, userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper -> wrapper.eq("user_account", userAccount).or().eq("email",userAccount));
        if (this.count(queryWrapper) == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号(邮箱)不存在");
        }
        //数据库
        String encryptPassword = DigestUtil.md5Hex((userPassword + SALT).getBytes(StandardCharsets.UTF_8));
        queryWrapper.eq("user_password", encryptPassword);
        if (this.count(queryWrapper) == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号(邮箱)或密码错误");
        }
        User user = userMapper.selectOne(queryWrapper);
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        //登录成功，将生成的token存入redis中
        String token = UUID.randomUUID().toString().replaceAll("-", "") + "";
        //存储
        String tokenKey = LOGIN_USER_KEY + token;
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("token", token);
        userMap.put("userInfo", userDTO);
        redisTemplate.opsForHash().putAll(tokenKey, userMap);
        //设置token有效期
        redisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.MINUTES);
        return userMap;
    }

    /**
     * 获取User的数据传输模型
     * @param request http请求
     * @return 返回userDTO
     */
    @Override
    public UserDTO toUserDTO(HttpServletRequest request){
        if (request == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "未收到请求");
        }
        // 1.获取请求头中的token
        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token)) {
            throw new BusinessException(ErrorCode.LOGIN_ERROR, "未获取到token");
        }
        // 2.基于token获取redis中的用户
        String key = LOGIN_USER_KEY + token;
        Object userInfo = redisTemplate.opsForHash().entries(key).get("userInfo");
        // 3.判断用户是否存在
        if (userInfo == null) {
            throw new BusinessException(ErrorCode.LOGIN_ERROR, "用户不存在");
        }
        return (UserDTO) userInfo;
    }

    /**
     * 修改用户信息
     * @param user 用户
     * @param request
     */
    @Override
    public void updateUser(User user, HttpServletRequest request) {
        Long userId = user.getUserId();
        if (userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User oldUser = this.getById(user.getUserId());
        if (oldUser == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        // 3. 触发更新
        this.baseMapper.updateById(user);
    }

    @Override
    public String updateAvatarUrl(MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile.isEmpty()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请上传图片");
        }
        //源文件名称
        String originalFilename = multipartFile.getOriginalFilename();
        if (StrUtil.isBlank(originalFilename)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请上传图片");
        }
//        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.')).toLowerCase();
        String suffix = FileUtil.extName(originalFilename);
        if (!FileUtils.IMAGE_EXTENSIONS.contains(suffix)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片格式有误");
        }
        DateTime dateTime = new DateTime();
        String lastFilePath;
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
        String folderName = File.separator + "avatars" + File.separator;
//        String relativePath = folderName + DateUtil.year(dateTime) + File.separator + DateUtil.month(dateTime) + File.separator + DateUtil.weekOfMonth(dateTime) + File.separator;
        String relativePath = DateUtil.year(dateTime) + File.separator + DateUtil.month(dateTime) + File.separator + DateUtil.weekOfMonth(dateTime) + File.separator;
        String filePath = "/www/wwwroot/xyp/temp" + relativePath;
        String fileUrl = null;
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            lastFilePath = filePath + File.separator + newFileName;
            out = new FileOutputStream(lastFilePath);
            out.write(multipartFile.getBytes());
//            fileUrl = "https://xiaoyuanpai.sjxbbd.top" + relativePath + newFileName;
            fileUrl = "https://xiaoyuanpai.sjxbbd.top/avatar/" + newFileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (fileUrl == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片上传失败");
        }
        UserDTO userDTO = toUserDTO(request);
        User user = this.baseMapper.selectById(userDTO.getUserId());
        user.setAvatar(fileUrl);
        this.updateById(user);
        return fileUrl;
    }

    /**
     * 是否为管理员
     * @param request 含token的请求
     * @return 返回true-管理员
     */
    public boolean isAdmin(HttpServletRequest request){
        UserDTO userDTO = toUserDTO(request);
        Integer userRole = userDTO.getUserRole();
        return userRole == ADMIN_ROLE;
    }


    /**
     * 脱敏
     * @param user
     * @return
     */

    /*@Override
    public User getSafetyUser(User user) {
        User safetyUser = new User();
        safetyUser.setUserId(user.getUserId());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setGender(user.getGender());
        safetyUser.setGrade(user.getGrade());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setCreateTime(user.getCreateTime());
        safetyUser.setUpdateIme(user.getUpdateIme());
        safetyUser.setAvatar(user.getAvatar());
        safetyUser.setStatus(user.getStatus());
        safetyUser.setIsDelete(user.getIsDelete());
        safetyUser.setUserRole(user.getUserRole());
        return safetyUser;
    }*/


}




