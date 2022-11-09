package com.sxu.xyp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sxu.xyp.mapper")
public class XypApplication {

    public static void main(String[] args) {
        SpringApplication.run(XypApplication.class, args);
    }

}
