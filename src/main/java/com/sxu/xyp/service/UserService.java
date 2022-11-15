package com.sxu.xyp.service;

import com.sxu.xyp.common.UserDTO;
import com.sxu.xyp.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-11-09 09:42:32
*/

public interface UserService extends IService<User> {

    long userRegister(String userAccount, String email,String userPassword, String checkPassword);

    String userLogin(String userAccount, String userPassword);

    UserDTO toUserDTO(HttpServletRequest request);

//    User getSafetyUser(User user);

}
