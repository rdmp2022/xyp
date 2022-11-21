package com.sxu.xyp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.common.ErrorCode;
import com.sxu.xyp.exception.BusinessException;
import com.sxu.xyp.model.domain.Articles;
import com.sxu.xyp.model.domain.Favorties;
import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.service.ArticlesService;
import com.sxu.xyp.service.FavortiesService;
import com.sxu.xyp.mapper.FavortiesMapper;
import com.sxu.xyp.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 86187
 * @description 针对表【favorties(用户-收藏表)】的数据库操作Service实现
 * @createDate 2022-11-14 21:58:30
 */
@Service
public class FavortiesServiceImpl extends ServiceImpl<FavortiesMapper, Favorties>
        implements FavortiesService {

    @Resource
    UserService userService;

    @Resource
    FavortiesMapper favortiesMapper;

    @Resource
    ArticlesService articlesService;

    //收藏文章
    @Override
    public Boolean collect(Long articleId, HttpServletRequest request) {
        UserDTO userDTO = userService.toUserDTO(request);
        Long userId = userDTO.getUserId();
        if (this.count(new QueryWrapper<Favorties>().eq("user_id", userId).eq("article_id", articleId)) != 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "已收藏该文章");
        }
        //文章收藏数加一
        Articles article = articlesService.getById(articleId);
        Long count = article.getFavoritesCount();
        UpdateWrapper<Articles> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("article_id",articleId);
        Articles articles = new Articles();
        articles.setFavoritesCount(count + 1);
        articlesService.update(articles,updateWrapper);
        //存入数据库
        Favorties favorties = new Favorties(userId, articleId);
        favortiesMapper.insert(favorties);
        return true;
    }

    //取消收藏
    @Override
    public Boolean UnCollect(Long articleId, HttpServletRequest request) {
        UserDTO userDTO = userService.toUserDTO(request);
        Long userId = userDTO.getUserId();
        if (this.count(new QueryWrapper<Favorties>().eq("user_id", userId).eq("article_id", articleId)) == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "未收藏该文章");
        }
        //文章收藏数减一
        Articles article = articlesService.getById(articleId);
        Long count = article.getFavoritesCount();
        if (count > 0) {
            UpdateWrapper<Articles> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("article_id",articleId);
            Articles articles = new Articles();
            articles.setFavoritesCount(count - 1);
            articlesService.update(articles,updateWrapper);
        }

        favortiesMapper.delete(new QueryWrapper<Favorties>().eq("user_id", userId).eq("article_id", articleId));
        return true;
    }

    //删除文章时删除对应数据
    @Override
    public Boolean deleteByArticleId(Long articleId) {
        if (favortiesMapper.delete(new QueryWrapper<Favorties>().eq("article_id", articleId)) != 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean isFavorite(Long userId, long articleId) {
        Integer count = favortiesMapper.selectCount(new QueryWrapper<Favorties>().eq("user_id", userId).eq("article_id", articleId));
        if (count == 0) {
            return false;
        }
        return true;
    }

}




