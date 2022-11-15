package com.sxu.xyp.model.domain.Article;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: TODO
 * @author: author
 * @date: 2022/11/15 22:38
 */
public class UnOpenArticles {
    private long articleId;

    private String title;

    private String summary;

    private List<String> tags;

    private long starCount;

    private String author;

    private LocalDateTime createTime;
}
