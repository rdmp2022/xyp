package com.sxu.xyp;

import com.sxu.xyp.mapper.UserMapper;
import com.sxu.xyp.service.LabelsService;
import com.sxu.xyp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.sxu.xyp.constant.UserConstant.LOGIN_USER_KEY;

@SpringBootTest
class XypApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private UserMapper userMapper;

    @Resource
    LabelsService labelsService;

    @Test
    void contextLoads() {
    }

    @Test
    void testRegister(){

    }

    @Test
    void testLogin(){

    }

    @Test
    void testAvatarUtl(){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set("test", "123", 3L, TimeUnit.MINUTES);
        System.out.println(redisTemplate.getExpire("test"));
    }
}
