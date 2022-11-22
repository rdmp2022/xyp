package com.sxu.xyp.controller;

import cn.hutool.core.bean.BeanUtil;
import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ErrorCode;
import com.sxu.xyp.common.ResultUtil;
import com.sxu.xyp.model.domain.User;
import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.exception.BusinessException;
import com.sxu.xyp.model.request.LoginRequest;
import com.sxu.xyp.model.request.RegisterRequest;
import com.sxu.xyp.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Api(value = "用户API", tags = "用户相关接口")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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
        Long userId = userDTO.getUserId();
        User user = userService.getBaseMapper().selectById(userId);
        UserDTO userDTO1 = BeanUtil.copyProperties(user, UserDTO.class);
        return ResultUtil.success(userDTO1);
    }

    @PutMapping("/update")
    public BaseResponse<UserDTO> updateById(@RequestBody User user, HttpServletRequest request){
        userService.updateUser(user, request);
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        return ResultUtil.success(userDTO);
    }

    @PutMapping("/updateAvatarUrl")
    public BaseResponse<String> updateAvatarUrl(@RequestParam MultipartFile multipartFile, HttpServletRequest request){
        String fileUrl = userService.updateAvatarUrl(multipartFile, request);
        return ResultUtil.success(fileUrl);
    }

    @GetMapping("/test")
    public BaseResponse<String> testGet(){
        return ResultUtil.success("get成功jenkins***");
    }
}
