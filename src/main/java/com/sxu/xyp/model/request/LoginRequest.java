package com.sxu.xyp.model.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 6628468128824150781L;

    private String userAccount;
    private String userPassword;
    private String remember;
}
