package com.sxu.xyp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 帖子_标签表
 * @TableName article_label
 */
@TableName(value ="article_label")
@Data
public class ArticleLabel implements Serializable {
    /**
     * 帖子ID
     */
    @TableId(value = "article_id")
    private Long article_id;

    /**
     * 标签ID
     */
    @TableField(value = "label_id")
    private Long label_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}