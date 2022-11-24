package com.sxu.xyp.model.params.CampusInfo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AddCampusInfoParams implements Serializable {
    /**
     * 发表的管理员id
     */
    private Integer adminId;

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
