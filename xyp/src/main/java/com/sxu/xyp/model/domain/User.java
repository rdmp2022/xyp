package com.sxu.xyp.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
    @TableId
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户昵称

     */
    private String username;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 电话号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态 0-正常
     */
    private Integer status;

    /**
     * 逻辑删除 0 - 正常 1 - 已删除
     */
    private Integer isDelete;

    /**
     * 权限 0-普通用户 1-管理员
     */
    private Integer userRole;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}