package com.sxu.xyp.model.domain.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: TODO
 * @author: author
 * @date: 2022/11/15 22:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnOpenArticles {

    private long articleId;

    private String title;

    private String summary;

    private List<String> tags;

    private long starCount;

    private String author;

    private LocalDateTime createTime;
}
