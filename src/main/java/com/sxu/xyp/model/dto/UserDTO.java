package com.sxu.xyp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
