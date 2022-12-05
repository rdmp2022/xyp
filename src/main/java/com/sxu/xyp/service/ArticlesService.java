package com.sxu.xyp.service;

import com.sxu.xyp.model.params.article.AddArticleParams;
import com.sxu.xyp.model.domain.Articles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.model.params.article.ArticleParam;
import com.sxu.xyp.model.params.article.UpdateArticleParams;
import com.sxu.xyp.model.params.label.LableParams;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 86187
* @description 针对表【articles(帖子表)】的数据库操作Service
* @createDate 2022-11-14 22:06:32
*/
public interface ArticlesService extends IService<Articles> {


    Long add(AddArticleParams addArticleParams, UserDTO user);


    Boolean delete(Long articleId, HttpServletRequest request);

    // 列出所有未展开的帖子的帖子
    List<ArticleParam> listAll(HttpServletRequest request);


    List<ArticleParam> listCollect(HttpServletRequest request);

    // 根据id获取帖子详情
    ArticleParam detail(Long articleId, HttpServletRequest request);


    Articles updateArticle(UpdateArticleParams updateArticleParams);

    //只显示我的帖子
    List<ArticleParam> listMyArticles(HttpServletRequest request);

    //判断是否收藏
    List<ArticleParam> toArticleParams(List<Articles> articles,HttpServletRequest request);

    // 根据时间排序
    List<ArticleParam> sortByTime(List<ArticleParam> articleParamList);


    List<ArticleParam> findFavoriteArticlesByUserId(@RequestParam Long userId, HttpServletRequest request);


    List<ArticleParam> toArticleParams(List<Articles> articles,Long userId);


    ArticleParam toArticleParam(Articles article,HttpServletRequest request);


    UserDTO findUserByUserId(Long userId, HttpServletRequest request);


    List<ArticleParam> findArticlesByUserId(Long userId, HttpServletRequest request);

    // 通过标签展示帖子
    List<ArticleParam> findArticleByLabel(LableParams lableParams, HttpServletRequest request);
}
