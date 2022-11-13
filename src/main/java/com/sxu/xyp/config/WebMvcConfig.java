package com.sxu.xyp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.sxu.xyp.utils.RefreshTokenInterceptor;

import javax.annotation.Resource;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")//允许哪些域访问
                .allowedMethods("*")//允许哪些方法访问
                .allowCredentials(true)//是否允许携带cookie
                .maxAge(3600)//设置浏览器询问的有效期
                .allowedHeaders("*");//
    }
    @Override
    public void addInterceptors (InterceptorRegistry registry){
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**");
    }
}
