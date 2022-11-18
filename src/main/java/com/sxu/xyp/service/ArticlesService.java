package com.sxu.xyp.service;

import com.sxu.xyp.model.params.AddArticleParams;
import com.sxu.xyp.model.domain.Articles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.model.params.ArticleParam;

import java.util.List;

/**
* @author 86187
* @description 针对表【articles(帖子表)】的数据库操作Service
* @createDate 2022-11-14 22:06:32
*/
public interface ArticlesService extends IService<Articles> {

    Long add(AddArticleParams addArticleParams, UserDTO user);

    Boolean remove(Long articleId);

    // 列出所有为展开的帖子的帖子
    List<ArticleParam> listAll();
}
