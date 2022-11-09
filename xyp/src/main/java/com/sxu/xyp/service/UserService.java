package com.sxu.xyp.service;

import com.sxu.xyp.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author DELL
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-11-09 09:42:32
*/
public interface UserService extends IService<User> {

    long userRegister(String userAccount, String userPassword, String checkPassword);

    User userLogin(String userAccount, String userPassword, HttpServletRequest httpServletRequest);

    User getSafetyUser(User user);

}
