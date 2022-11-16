package com.sxu.xyp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ResultUtil;

import com.sxu.xyp.model.domain.Article.AddArticle;
import com.sxu.xyp.model.domain.Article.Articles;
import com.sxu.xyp.model.domain.Article.OpenArticles;
import com.sxu.xyp.model.domain.ArticleLabel;
import com.sxu.xyp.model.domain.Labels;
import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.service.ArticleLabelService;
import com.sxu.xyp.service.ArticlesService;
import com.sxu.xyp.mapper.ArticlesMapper;
import com.sxu.xyp.service.LabelsService;
import com.sxu.xyp.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 86187
* @description 针对表【articles(帖子表)】的数据库操作Service实现
* @createDate 2022-11-16 11:22:16
*/
@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Articles> implements ArticlesService{



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
        this.save(article);

        // 标签操作
        Long id = article.getArticleId();
        List<String> tags = addArticle.getTags();
        for (String tag : tags) {
            //不存在相同标签
            Long labelId = labelsService.addLabel(tag);
            ArticleLabel articleLabel = new ArticleLabel(id, labelId);
            articleLabelService.save(articleLabel);
        }
        return 1L;
    }
}




