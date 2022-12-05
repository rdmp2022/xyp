package com.sxu.xyp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.common.ErrorCode;
import com.sxu.xyp.exception.BusinessException;
import com.sxu.xyp.model.domain.Favorties;
import com.sxu.xyp.model.domain.User;
import com.sxu.xyp.model.params.article.AddArticleParams;
import com.sxu.xyp.model.domain.Articles;
import com.sxu.xyp.model.domain.ArticleLabel;
import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.model.params.article.ArticleParam;
import com.sxu.xyp.model.params.article.UpdateArticleParams;
import com.sxu.xyp.model.params.label.LabelParam;
import com.sxu.xyp.model.params.label.LableParams;
import com.sxu.xyp.service.*;
import com.sxu.xyp.mapper.ArticlesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
        List<LabelParam> tags = addArticleParams.getTags();
        for (LabelParam tag : tags) {
            //标签id
            Long labelId = labelsService.addLabel(tag.getLabel());
            if (labelId != 0L) {
                ArticleLabel articleLabel = new ArticleLabel(id, Long.parseLong(tag.getValue()));
                articleLabelService.save(articleLabel);
            }
        }
        return id;
    }

    @Override
    public Boolean delete(Long articleId, HttpServletRequest request) {
        Articles article = this.getById(articleId);
        if (!Objects.equals(userService.toUserDTO(request).getUserId(), article.getUserId())) {
            throw new BusinessException(ErrorCode.LOGIN_ERROR, "没有权限");
        }
        return this.removeById(articleId) && articleLabelService.deleteById(articleId) && favortiesService.deleteByArticleId(articleId);
    }

    @Override
    public List<ArticleParam> listAll(HttpServletRequest request) {
        List<Articles> articlesList = this.list();
        List<ArticleParam> articlesParamList = this.toArticleParams(articlesList, request);
        //Collections.reverse(articlesParamList);
        return articlesParamList;
    }

    @Override
    public List<ArticleParam> listCollect(HttpServletRequest request) {
        Long userId = userService.toUserDTO(request).getUserId();
        ArrayList<Articles> articles = new ArrayList<>();
        List<Favorties> favortieList = favortiesService.list(new QueryWrapper<Favorties>().eq("user_id", userId));
        for (Favorties favorties : favortieList) {
            articles.add(this.getById(favorties.getArticleId()));
        }
        return  toArticleParams(articles, request);
    }

    @Override
    public ArticleParam detail(Long articleId, HttpServletRequest request) {
        Articles articles = this.getById(articleId);
        ArticleParam articleParam = this.toArticleParam(articles, request);
        return articleParam;
    }


    @Override
    public Articles updateArticle(UpdateArticleParams updateArticleParams) {
        Articles article = this.getById(updateArticleParams.getArticleId());
        article.setContent(updateArticleParams.getContent());
        article.setSummary(updateArticleParams.getSummary());
        articlesMapper.update(article, new QueryWrapper<Articles>().eq("article_id", updateArticleParams.getArticleId()));
        return article;
    }

    @Override
    public List<ArticleParam> listMyArticles(HttpServletRequest request) {
        UserDTO user = userService.toUserDTO(request);
        List<Articles> articles = articlesMapper.selectList(new QueryWrapper<Articles>().eq("user_id", user.getUserId()));
        List<ArticleParam> myArticlesParam = this.toArticleParams(articles, request);
        return sortByTime(myArticlesParam);
    }

    @Override
    public List<ArticleParam> toArticleParams(List<Articles> articles, HttpServletRequest request) {

        //是否显示收藏
        UserDTO userDTO = null;
        int flag = 0;
        if (request == null) {
            flag = 0;
        } else if (StrUtil.isBlank(request.getHeader("authorization"))) {
            flag = 0;
        } else {
            userDTO = userService.toUserDTO(request);
            flag = 1;
        }
        List<ArticleParam> myArticlesParam = BeanUtil.copyToList(articles, ArticleParam.class);
        for (ArticleParam articleParam : myArticlesParam) {
            //作者名称
            articleParam.setUsername(userService.getById(articleParam.getUserId()).getUsername());
            //标签
            List<String> list = articleLabelService.getAllLabelID(articleParam.getArticleId());
            if (flag == 1) {
                if (favortiesService.isFavorite(userDTO.getUserId(), articleParam.getArticleId())) {
                    articleParam.setIsFavorite(1);
                } else {
                    articleParam.setIsFavorite(0);
                }
            } else {
                articleParam.setIsFavorite(0);
            }
            articleParam.setTags(list);
        }
        return sortByTime(myArticlesParam);
    }

    @Override
    public List<ArticleParam> sortByTime(List<ArticleParam> articleParamList) {
        Collections.sort(articleParamList, new Comparator<ArticleParam>() {
            @Override
            public int compare(ArticleParam o1, ArticleParam o2) {
                //return o1.getUpdateTime().compareTo(o2.getUpdateTime());
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });
        return articleParamList;
    }

    @Override
    public List<ArticleParam> findFavoriteArticlesByUserId(Long userId, HttpServletRequest request) {
        ArrayList<Articles> articles = new ArrayList<>();
        List<Favorties> favortieList = favortiesService.list(new QueryWrapper<Favorties>().eq("user_id", userId));
        for (Favorties favorties : favortieList) {
            articles.add(this.getById(favorties.getArticleId()));
        }
        return this.toArticleParams(articles, request);
    }


    @Override
    public List<ArticleParam> toArticleParams(List<Articles> articles, Long userId) {

        List<ArticleParam> myArticlesParam = BeanUtil.copyToList(articles, ArticleParam.class);
        for (ArticleParam articleParam : myArticlesParam) {
            //作者名称
            articleParam.setUsername(userService.getById(articleParam.getUserId()).getUsername());
            //标签
            List<String> list = articleLabelService.getAllLabelID(articleParam.getArticleId());
            if (favortiesService.isFavorite(userId, articleParam.getArticleId())) {
                articleParam.setIsFavorite(1);
            } else {
                articleParam.setIsFavorite(0);
            }
            articleParam.setTags(list);
        }
        //按照时间排序
        return  sortByTime(myArticlesParam);
    }

    @Override
    public ArticleParam toArticleParam(Articles article, HttpServletRequest request) {
        //是否显示收藏
        UserDTO userDTO = null;
        int flag = 0;
        if (request == null) {
            flag = 0;
        } else if (StrUtil.isBlank(request.getHeader("authorization"))) {
            flag = 0;
        } else {
            userDTO = userService.toUserDTO(request);
            flag = 1;
        }
        ArticleParam articleParam = BeanUtil.copyProperties(article, ArticleParam.class);
        //作者名称
        articleParam.setUsername(userService.getById(articleParam.getUserId()).getUsername());
        //标签
        List<String> list = articleLabelService.getAllLabelID(articleParam.getArticleId());
        if (flag == 1) {
            if (favortiesService.isFavorite(userDTO.getUserId(), articleParam.getArticleId())) {
                articleParam.setIsFavorite(1);
            } else {
                articleParam.setIsFavorite(0);
            }
        } else {
            articleParam.setIsFavorite(0);
        }
        articleParam.setTags(list);

        return articleParam;
    }

    @Override
    public UserDTO findUserByUserId(Long userId, HttpServletRequest request) {
        if (userId == null || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getById(userId);
        return BeanUtil.copyProperties(user, UserDTO.class);
    }

    @Override
    public List<ArticleParam> findArticlesByUserId(Long userId, HttpServletRequest request) {
        if (userId == null || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<Articles> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Articles> articlesList = this.list(queryWrapper);
        return this.toArticleParams(articlesList, request);
    }

    @Override
    public List<ArticleParam> findArticleByLabel(LableParams lableParams, HttpServletRequest request) {
        List<Long> articleIds = articleLabelService.getArticleId(lableParams);
        ArrayList<Articles> articles = new ArrayList<>();
        for (Long articleId : articleIds) {
            articles.add(this.getById(articleId));
        }
        return this.toArticleParams(articles, request);
    }
}




