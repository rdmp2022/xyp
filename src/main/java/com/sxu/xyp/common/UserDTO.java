package com.sxu.xyp.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private Long userId;

    private String userAccount;

    private String username;

    private Integer gender;

    private Integer grade;

    private String phone;

    private String email;

    private String avatar;

    private Integer userRole;
}
