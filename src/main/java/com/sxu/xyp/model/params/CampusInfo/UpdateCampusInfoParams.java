package com.sxu.xyp.model.params.CampusInfo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateCampusInfoParams implements Serializable {

    /**
     * 资讯id
     */
    private Integer id;

    /**
     * 资讯标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 内容
     */
    private String content;

}
