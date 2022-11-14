package com.sxu.xyp.model.domain;

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
    @TableId(value = "banner_id")
    private Long bannerId;

    /**
     * 轮播图url
     */
    @TableField(value = "banner_url")
    private String bannerUrl;

    /**
     * 是否展示 ：1 展示， 0 不展示
     */
    @TableField(value = "is_show")
    private Integer isShow;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 轮播图ID
     */
    public Long getBannerId() {
        return bannerId;
    }

    /**
     * 轮播图ID
     */
    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    /**
     * 轮播图url
     */
    public String getBannerUrl() {
        return bannerUrl;
    }

    /**
     * 轮播图url
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    /**
     * 是否展示 ：1 展示， 0 不展示
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * 是否展示 ：1 展示， 0 不展示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}