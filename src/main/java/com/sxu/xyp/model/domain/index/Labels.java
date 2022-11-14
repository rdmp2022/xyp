package com.sxu.xyp.model.domain.index;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 标签表
 * @TableName labels
 */
@TableName(value ="labels")
public class Labels implements Serializable {
    /**
     * 标签ID
     */
    @TableId(value = "label_id", type = IdType.AUTO)
    private Long labelId;

    /**
     * 标签名称
     */
    @TableField(value = "label_name")
    private String labelName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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

    /**
     * 标签名称
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * 标签名称
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}