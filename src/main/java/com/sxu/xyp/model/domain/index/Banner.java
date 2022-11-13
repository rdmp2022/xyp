package com.sxu.xyp.model.domain.index;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 轮播图表
 * @TableName banner
 */
@TableName(value ="banner")
public class Banner implements Serializable {
    /**
     * 轮播图ID
     */
    @TableId
    private Long banner_id;

    /**
     * 轮播图url
     */
    private String banner_url;

    /**
     * 是否展示 ：1 展示， 0 不展示
     */
    private Integer is_show;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 轮播图ID
     */
    public Long getBanner_id() {
        return banner_id;
    }

    /**
     * 轮播图ID
     */
    public void setBanner_id(Long banner_id) {
        this.banner_id = banner_id;
    }

    /**
     * 轮播图url
     */
    public String getBanner_url() {
        return banner_url;
    }

    /**
     * 轮播图url
     */
    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    /**
     * 是否展示 ：1 展示， 0 不展示
     */
    public Integer getIs_show() {
        return is_show;
    }

    /**
     * 是否展示 ：1 展示， 0 不展示
     */
    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }
}