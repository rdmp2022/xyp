package com.sxu.xyp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.common.ErrorCode;
import com.sxu.xyp.common.UserDTO;
import com.sxu.xyp.exception.BusinessException;
import com.sxu.xyp.model.domain.User;
import com.sxu.xyp.service.UserService;
import com.sxu.xyp.mapper.UserMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static com.sxu.xyp.constant.UserConstant.*;

/**
* @author DELL
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

    @Override
    public String userLogin(String userAccount, String userPassword) {
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
        redisTemplate.opsForValue().set(tokenKey, JSONUtil.toJsonStr(userDTO));
        //设置token有效期
        redisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.MINUTES);
        return token;
    }

    @Override
    public UserDTO toUserDTO(HttpServletRequest request){
        // 1.获取请求头中的token
        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token)) {
            throw new BusinessException(ErrorCode.LOGIN_ERROR, "未获取到token");
        }
        // 2.基于TOKEN获取redis中的用户
        String key = LOGIN_USER_KEY + token;
        String userJsonStr = (String) redisTemplate.opsForValue().get(key);
        // 3.判断用户是否存在
        if (StrUtil.isBlank(userJsonStr)) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "用户不存在");
        }
        return JSONUtil.toBean(userJsonStr, UserDTO.class);
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




