package com.sxu.xyp.service;

import com.sxu.xyp.model.domain.Article.AddArticle;
import com.sxu.xyp.model.domain.Article.Articles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxu.xyp.model.dto.UserDTO;

/**
* @author 86187
* @description 针对表【articles(帖子表)】的数据库操作Service
* @createDate 2022-11-14 22:06:32
*/
public interface ArticlesService extends IService<Articles> {

    Long add(AddArticle addArticle, UserDTO user);
}
