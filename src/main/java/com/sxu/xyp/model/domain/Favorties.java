package com.sxu.xyp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 用户-收藏表
 * @TableName favorties
 */
@TableName(value ="favorties")
public class Favorties implements Serializable {
    /**
     * 用户ID
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 帖子ID
     */
    @TableField(value = "article_id")
    private Long articleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 帖子ID
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 帖子ID
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}