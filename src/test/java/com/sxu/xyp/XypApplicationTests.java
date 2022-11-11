package com.sxu.xyp;

import com.sxu.xyp.mapper.UserMapper;
import com.sxu.xyp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class XypApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testRegister(){
        System.out.println(userMapper.selectList(null));
    }

    @Test
    void testLogin(){
        redisTemplate.opsForValue().set("test", 1);
    }

}
