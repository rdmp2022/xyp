package com.sxu.xyp.model.params;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description: TODO
 * @author: author
 * @date: 2022/11/18 11:24
 */
@Data
public class ArticleParam {
    private String title;

    private String summary;

    private List<String> tags;

    private Long favoritesCount;

    private String username;

    private Date updateTime;


}
