package com.sxu.xyp.service;

import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
* @author
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-11-09 09:42:32
*/

public interface UserService extends IService<User> {

    long userRegister(String userAccount, String email,String userPassword, String checkPassword);

    Map<String, Object> userLogin(String userAccount, String userPassword);

    UserDTO toUserDTO(HttpServletRequest request);

    void updateUser(User user, HttpServletRequest request);

    String updateAvatarUrl(MultipartFile multipartFile, HttpServletRequest request);

    UserDTO currentUser(HttpServletRequest request);

    Boolean isAdmin(HttpServletRequest request);

    Boolean isAdmin(Integer userId);

//    User getSafetyUser(User user);

}
