package com.sxu.xyp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 帖子_标签表
 * @TableName article_label
 */
@TableName(value ="article_label")
public class ArticleLabel implements Serializable {
    /**
     * 帖子ID
     */
    @TableId(value = "article_id")
    private Long articleId;

    /**
     * 标签ID
     */
    @TableField(value = "label_id")
    private Long labelId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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

    /**
     * 标签ID
     */
    public Long getLabelId() {
        return labelId;
    }

    /**
     * 标签ID
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
}