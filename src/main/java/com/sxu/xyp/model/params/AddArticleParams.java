package com.sxu.xyp.model.params;

import lombok.Data;


import java.util.List;

/**
 * @description: TODO
 * @author: author
 * @date: 2022/11/16 11:36
 */
@Data
public class AddArticleParams {

    private String title;

    private String summary;

    private String content;

    private List<String> tags;

}
