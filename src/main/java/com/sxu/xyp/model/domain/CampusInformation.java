package com.sxu.xyp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName campus_information
 */
@TableName(value ="campus_information")
@Data
public class CampusInformation implements Serializable {
    /**
     * 资讯id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 发表的管理员id
     */
    @TableField(value = "admin_id")
    private Integer adminId;

    /**
     * 资讯标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 摘要
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 封面图片
     */
    @TableField(value = "cover_photo")
    private String coverPhoto;

    /**
     * 0 - 未删除 1 - 逻辑删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}