package com.sxu.xyp.model.params.article;

import lombok.Data;

/**
 * @description: TODO
 * @author: author
 * @date: 2022/11/20 13:43
 */
@Data
public class UpdateArticleParams {

    private Long articleId;

    private String summary;

    private String content;
}
