package com.sxu.xyp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 帖子表
 * @TableName articles
 */
@TableName(value ="articles")
@Data
public class Articles implements Serializable {
    /**
     * 帖子ID
     */
    @TableId(value = "article_id")
    private Long article_id;

    /**
     * 发表用户ID
     */
    @TableField(value = "user_id")
    private Long user_id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 摘要
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 浏览量
     */
    @TableField(value = "views")
    private Long views;

    /**
     * 评论总数
     */
    @TableField(value = "comment_count")
    private Long comment_count;

    /**
     * 收藏总数
     */
    @TableField(value = "favorites_count")
    private Long favorites_count;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "updateTime")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}