package com.sxu.xyp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sxu.xyp.model.params.AddArticleParams;
import com.sxu.xyp.model.domain.Articles;
import com.sxu.xyp.model.domain.Article.UnOpenArticles;
import com.sxu.xyp.model.domain.ArticleLabel;
import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.service.ArticleLabelService;
import com.sxu.xyp.service.ArticlesService;
import com.sxu.xyp.mapper.ArticlesMapper;
import com.sxu.xyp.service.LabelsService;

import org.springframework.stereotype.Service;


import javax.annotation.Resource;
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




