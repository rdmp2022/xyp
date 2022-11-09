package com.sxu.xyp.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRequest implements Serializable {
    private static final long serialVersionUID = 6565417968047318126L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
