package com.sxu.xyp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.model.domain.Favorties;
import com.sxu.xyp.model.params.AddArticleParams;
import com.sxu.xyp.model.domain.Articles;
import com.sxu.xyp.model.domain.ArticleLabel;
import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.model.params.ArticleParam;
import com.sxu.xyp.model.params.UpdateArticleParams;
import com.sxu.xyp.service.*;
import com.sxu.xyp.mapper.ArticlesMapper;

import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 86187
 * @description 针对表【articles(帖子表)】的数据库操作Service实现
 * @createDate 2022-11-16 11:22:16
 */
@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Articles> implements ArticlesService {


    @Resource
    LabelsService labelsService;

    @Resource
    ArticleLabelService articleLabelService;

    @Resource
    UserService userService;

    @Resource
    ArticlesMapper articlesMapper;

    @Resource
    FavortiesService favortiesService;

    @Override
    public Long add(AddArticleParams addArticleParams, UserDTO user) {
        Articles article = new Articles();
        article.setTitle(addArticleParams.getTitle());
        article.setContent(addArticleParams.getContent());
        article.setSummary(addArticleParams.getSummary());
        article.setViews(0L);
        article.setFavoritesCount(0L);
        article.setUserId(user.getUserId());
        article.setCommentCount(0L);
        this.save(article);

        // 标签操作
        // 用户id
        Long id = article.getArticleId();
        List<String> tags = addArticleParams.getTags();
        for (String tag : tags) {
            //标签id
            Long labelId = labelsService.addLabel(tag);
            if (labelId != 0L) {
                ArticleLabel articleLabel = new ArticleLabel(id, labelId);
                articleLabelService.save(articleLabel);
            }
        }
        return id;
    }

    @Override
    public Boolean remove(Long articleId) {
        if (this.removeById(articleId) && articleLabelService.removeById(articleId) && favortiesService.deleteByArticleId(articleId)) {
            return true;
        }
        return false;
    }

    @Override
    public List<ArticleParam> listAll() {
        List<Articles> articlesList = this.list();
        List<ArticleParam> articlesParam = this.toArticleParam(articlesList);
        return articlesParam;
    }

    @Override
    public ArticleParam detail(Long articleId) {
        Articles articles = this.getById(articleId);
        ArticleParam articleParam = BeanUtil.copyProperties(articles, ArticleParam.class);
        articleParam.setUsername(userService.getById(articleParam.getUserId()).getUsername());
        List<String> list = articleLabelService.getAllLabelID(articleParam.getArticleId());
        articleParam.setTags(list);
        return articleParam;
    }

    @Override
    public Articles update(UpdateArticleParams updateArticleParams) {
        Articles article = this.getById(updateArticleParams.getArticleId());
        article.setContent(updateArticleParams.getContent());
        article.setSummary(updateArticleParams.getSummary());
        return article;
    }

    @Override
    public List<ArticleParam> listMyArticles(HttpServletRequest request) {
        UserDTO user = userService.toUserDTO(request);
        List<Articles> articles = articlesMapper.selectList(new QueryWrapper<Articles>().eq("user_id", user.getUserId()));
        List<ArticleParam> myArticlesParam = this.toArticleParam(articles);
        return myArticlesParam;
    }

    @Override
    public List<ArticleParam> toArticleParam(List<Articles> articles) {
        List<ArticleParam> myArticlesParam = BeanUtil.copyToList(articles, ArticleParam.class);
        for (ArticleParam articleParam : myArticlesParam) {
            //作者名称
            articleParam.setUsername(userService.getById(articleParam.getUserId()).getUsername());
            //标签
            List<String> list = articleLabelService.getAllLabelID(articleParam.getArticleId());
            articleParam.setTags(list);
        }
        return myArticlesParam;
    }


}




