package com.sxu.xyp.service.impl;
import java.util.Date;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.common.ErrorCode;
import com.sxu.xyp.exception.BusinessException;
import com.sxu.xyp.model.domain.User;
import com.sxu.xyp.service.UserService;
import com.sxu.xyp.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

import static com.sxu.xyp.constant.UserConstant.LOGIN_STATUS;
import static com.sxu.xyp.constant.UserConstant.SALT;

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
        if (StrUtil.hasBlank(userAccount, email,userPassword, checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号长度不能少于4");
        }
        if (email.length() < 4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱长度不能少于8");
        }
        if (userPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不能少于8");
        }
        if (!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入密码必须相同");
        }
        if (userAccount == email){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号和邮箱不能重复");
        }
        //账号不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount).or().eq("email",email);
        int count = this.count(queryWrapper);
        if (count > 0){
            return 0;
            //throw new BusinessException(ErrorCode.PARAMS_ERROR, "存在相同账号或者邮箱");
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
    public User userLogin(String userAccount, String userPassword, HttpServletRequest httpServletRequest) {
        if (StrUtil.hasBlank(userAccount, userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号长度不能少于4");
        }
        if (userPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不能少于8");
        }
        //数据库
        String encryptPassword = DigestUtil.md5Hex((userPassword + SALT).getBytes(StandardCharsets.UTF_8));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount).or().eq("email",userAccount);
        queryWrapper.eq("user_password", encryptPassword);
        if (this.count(queryWrapper) == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号(邮箱)或密码错误");
        }
        User user = userMapper.selectOne(queryWrapper);
        User safetyUser = getSafetyUser(user);
        //登录态存入session
        httpServletRequest.getSession().setAttribute(LOGIN_STATUS, safetyUser);
        return safetyUser;
    }


    /**
     * 脱敏
     * @param user
     * @return
     */

    //1.
    @Override
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
    }


}




