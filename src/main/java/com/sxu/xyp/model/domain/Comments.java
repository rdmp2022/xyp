package com.sxu.xyp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 评论表
 * @TableName comments
 */
@TableName(value ="comments")
@Data
public class Comments implements Serializable {
    /**
     * 评论ID
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long comment_id;

    /**
     * 发表用户ID
     */
    @TableField(value = "user_id")
    private Long user_id;

    /**
     * 帖子ID
     */
    @TableField(value = "article_id")
    private Long article_id;

    /**
     * 评论时间
     */
    @TableField(value = "comment_date")
    private LocalDateTime comment_date;

    /**
     * 评论内容
     */
    @TableField(value = "comment_content")
    private String comment_content;

    /**
     * 父评论ID
     */
    @TableField(value = "parent_comment_id")
    private Long parent_comment_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}