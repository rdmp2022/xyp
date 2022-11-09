package com.sxu.xyp;

import com.sxu.xyp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class XypApplicationTests {

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testRegister(){
        String userAccount = "wmyaoo";
        String userPassword = "11111111";
        String checkPassword = "11111111";
        userService.userRegister(userAccount, userPassword, checkPassword);
    }



}
