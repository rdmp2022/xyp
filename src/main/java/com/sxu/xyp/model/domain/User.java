package com.sxu.xyp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 账号
     */
    @TableField(value = "user_account")
    private String userAccount;

    /**
     * 密码
     */
    @TableField(value = "user_password")
    private String userPassword;

    /**
     * 用户昵称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 年级
     */
    @TableField(value = "grade")
    private Integer grade;

    /**
     * 电话号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_ime")
    private LocalDateTime updateIme;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 状态 0-正常
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 逻辑删除 0 - 正常 1 - 已删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 权限 0-普通用户 1-管理员
     */
    @TableField(value = "user_role")
    private Integer userRole;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}