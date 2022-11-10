package com.sxu.xyp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户-收藏表
 * @TableName favorties
 */
@TableName(value ="favorties")
@Data
public class Favorties implements Serializable {
    /**
     * 用户ID
     */
    @TableId(value = "user_id")
    private Long user_id;

    /**
     * 帖子ID
     */
    @TableField(value = "article_id")
    private Long article_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}