package com.sxu.xyp;

import com.sxu.xyp.mapper.UserMapper;
import com.sxu.xyp.service.LabelsService;
import com.sxu.xyp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
        String userAccount = "wmyaoo2";
        String userPassword = "11111111";
        String checkPassword = "11111111";
        userService.userRegister(userAccount,"1234@122143.com", userPassword, checkPassword);
    }

    @Test
    void testLogin(){
        Map<Object, Object> map = redisTemplate.opsForHash().entries(LOGIN_USER_KEY + 3);
        System.out.println(map);
    }
}
