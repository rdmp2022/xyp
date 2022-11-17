package com.sxu.xyp.utils;

import cn.hutool.core.util.StrUtil;
import com.sxu.xyp.model.dto.UserDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.TimeUnit;

import static com.sxu.xyp.constant.UserConstant.LOGIN_USER_KEY;
import static com.sxu.xyp.constant.UserConstant.LOGIN_USER_TTL;

@Configuration
public class RefreshTokenInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求头中的token
        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token)) {
            return true;
        }
        // 2.基于TOKEN获取redis中的用户
        String key = LOGIN_USER_KEY + token;
        Object userInfo = redisTemplate.opsForHash().entries(key).get("userInfo");
        // 3.判断用户是否存在
        if (userInfo == null) {
            return true;
        }
        UserDTO userDTO = (UserDTO) userInfo;
        // 6.存在，保存用户信息到 ThreadLocal
        UserHolder.saveUser(userDTO);
        // 7.刷新token有效期
        redisTemplate.expire(key, LOGIN_USER_TTL, TimeUnit.HOURS);
        // 8.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        UserHolder.removeUser();
    }
}
	
