package com.sxu.xyp.common;

public enum ErrorCode {
    SUCCESS(0, "成功", ""),
    PARAMS_ERROR(30000, "请求参数错误", ""),
    NULL_ERROR(30001, "请求数据为空", ""),
    ROLE_ERROR(30002, "权限错误", ""),
    LOGIN_ERROR(30003, "未登录", "");

    private final int code;
    private final String message;
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
