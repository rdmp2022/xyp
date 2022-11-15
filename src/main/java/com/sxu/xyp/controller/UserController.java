package com.sxu.xyp.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ErrorCode;
import com.sxu.xyp.common.ResultUtil;
import com.sxu.xyp.common.UserDTO;
import com.sxu.xyp.exception.BusinessException;
import com.sxu.xyp.model.request.LoginRequest;
import com.sxu.xyp.model.request.RegisterRequest;
import com.sxu.xyp.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
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
    public BaseResponse<Map<String, Object>> userLogin(@RequestBody LoginRequest loginRequest) {
        if (loginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = loginRequest.getUserAccount();
        String userPassword = loginRequest.getUserPassword();

        Map<String, Object> userMap = userService.userLogin(userAccount, userPassword);
        return ResultUtil.success(userMap);
    }

    @GetMapping("/current")
    public BaseResponse<UserDTO> currentUser(HttpServletRequest request){
        UserDTO userDTO = userService.toUserDTO(request);
        return ResultUtil.success(userDTO);
    }

    @GetMapping("/test")
    public BaseResponse<String> testGet(){
        return ResultUtil.success("get成功jenkins***");
    }
}
