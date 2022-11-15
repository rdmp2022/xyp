package com.sxu.xyp.model.domain.Article;

import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: 帖子展示（未展开）
 * @author: author
 * @date: 2022/11/15 10:17
 */
public class OpenArticles {

    private long articleId;

    private String title;

    private String summary;

    private String content;

    private List<String> tags;

    private long starCount;

    private String author;

    private LocalDateTime createTime;
}
