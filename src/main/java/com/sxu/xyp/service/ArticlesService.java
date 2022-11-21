package com.sxu.xyp.service;

import com.sxu.xyp.model.params.AddArticleParams;
import com.sxu.xyp.model.domain.Articles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.model.params.ArticleParam;
import com.sxu.xyp.model.params.UpdateArticleParams;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 86187
* @description 针对表【articles(帖子表)】的数据库操作Service
* @createDate 2022-11-14 22:06:32
*/
public interface ArticlesService extends IService<Articles> {

    Long add(AddArticleParams addArticleParams, UserDTO user);

    Boolean remove(Long articleId);

    // 列出所有未展开的帖子的帖子
    List<ArticleParam> listAll(HttpServletRequest request);

    // 根据id获取帖子详情
    ArticleParam detail(Long articleId, HttpServletRequest request);

    Articles update(UpdateArticleParams updateArticleParams);

    //只显示我的帖子
    List<ArticleParam> listMyArticles(HttpServletRequest request);

    //判断是否收藏

    List<ArticleParam> toArticleParams(List<Articles> articles,HttpServletRequest request);

    ArticleParam toArticleParam(Articles article,HttpServletRequest request);
}
