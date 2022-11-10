package com.sxu.xyp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 标签表
 * @TableName labels
 */
@TableName(value ="labels")
@Data
public class Labels implements Serializable {
    /**
     * 标签ID
     */
    @TableId(value = "label_id", type = IdType.AUTO)
    private Long label_id;

    /**
     * 标签名称
     */
    @TableField(value = "label_name")
    private String label_name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}