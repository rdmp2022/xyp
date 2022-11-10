package com.sxu.xyp.controller;

import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ResultUtil;
import com.sxu.xyp.model.domain.User;

import com.sxu.xyp.service.UserService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:3579"})
@Api(tags = "用户API")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "账号不小于4位不可包含特殊字符，密码不小于8位，账号密码必须一致", httpMethod = "POST")
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(
            @ApiParam(name = "userAccount", value = "账号", required = true) @RequestParam String userAccount,
            @ApiParam(name = "userPassword", value = "密码", required = true) @RequestParam String userPassword,
            @ApiParam(name = "checkPassword", value = "确认密码", required = true) @RequestParam String checkPassword

    ){
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtil.success(result);
    }

    @ApiOperation(value = "用户登录", notes = "账号不小于4位不可包含特殊字符，密码不小于8位", httpMethod = "POST")
    @PostMapping("/login")
    public BaseResponse<User> userLogin(
            @ApiParam(name = "userAccount", value = "账号", required = true) @RequestParam String userAccount,
            @ApiParam(name = "userPassword", value = "密码", required = true) @RequestParam String userPassword,
            HttpServletRequest httpServletRequest
    ){
        User user = userService.userLogin(userAccount, userPassword, httpServletRequest);
        return ResultUtil.success(user);
    }


}
