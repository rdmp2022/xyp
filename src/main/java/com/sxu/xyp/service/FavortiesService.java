package com.sxu.xyp.service;


import com.sxu.xyp.model.domain.Favorties;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author 86187
* @description 针对表【favorties(用户-收藏表)】的数据库操作Service
* @createDate 2022-11-14 21:58:30
*/
public interface FavortiesService extends IService<Favorties> {
    Boolean collect(Long articleId, HttpServletRequest request);

    Boolean UnCollect(Long articleId, HttpServletRequest request);

    Boolean deleteByArticleId(Long articleId);

    Boolean isFavorite(Long userId, long articleId);
}
