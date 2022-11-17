package com.sxu.xyp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ResultUtil;

import com.sxu.xyp.model.domain.Article.AddArticle;
import com.sxu.xyp.model.domain.Article.Articles;
import com.sxu.xyp.model.domain.Article.OpenArticles;
import com.sxu.xyp.model.domain.Article.UnOpenArticles;
import com.sxu.xyp.model.domain.ArticleLabel;
import com.sxu.xyp.model.domain.Labels;
import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.service.ArticleLabelService;
import com.sxu.xyp.service.ArticlesService;
import com.sxu.xyp.mapper.ArticlesMapper;
import com.sxu.xyp.service.LabelsService;

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


    @Override
    public Long add(AddArticle addArticle, UserDTO user) {
        Articles article = new Articles();
        article.setTitle(addArticle.getTitle());
        article.setContent(addArticle.getContent());
        article.setSummary(addArticle.getSummary());
        article.setViews(0L);
        article.setFavoritesCount(0L);
        article.setUserId(user.getUserId());
        article.setCommentCount(0L);
        this.save(article);

        // 标签操作
        // 用户id
        Long id = article.getArticleId();
        List<String> tags = addArticle.getTags();
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
        if (this.removeById(articleId) && articleLabelService.removeById(articleId)) {
            return true;
        }
        return false;
    }


    @Override
    public List<UnOpenArticles> listAll() {
        List<Articles> articles = this.list();
        List<UnOpenArticles> unOpenArticles = BeanUtil.copyToList (articles, UnOpenArticles.class);
        return unOpenArticles;
    }

}




