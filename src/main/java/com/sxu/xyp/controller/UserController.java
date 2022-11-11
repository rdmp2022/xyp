package com.sxu.xyp.controller;

import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ErrorCode;
import com.sxu.xyp.common.ResultUtil;
import com.sxu.xyp.exception.BusinessException;
import com.sxu.xyp.model.domain.User;
import com.sxu.xyp.model.request.LoginRequest;
import com.sxu.xyp.model.request.RegisterRequest;
import com.sxu.xyp.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:3579"})
@Api(value = "用户API")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = registerRequest.getUserAccount();
        String email = registerRequest.getEmail();
        String userPassword = registerRequest.getUserPassword();
        String checkPassword = registerRequest.getCheckPassword();
        long result = userService.userRegister(userAccount, email, userPassword, checkPassword);
        return ResultUtil.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) {
        if (loginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = loginRequest.getUserAccount();
        String userPassword = loginRequest.getUserPassword();
        return userService.userLogin(userAccount, userPassword, httpServletRequest);
    }


}
