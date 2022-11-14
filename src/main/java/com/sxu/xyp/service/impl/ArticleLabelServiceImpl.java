package com.sxu.xyp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.model.domain.ArticleLabel;
import com.sxu.xyp.service.ArticleLabelService;
import com.sxu.xyp.mapper.ArticleLabelMapper;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【article_label(帖子_标签表)】的数据库操作Service实现
* @createDate 2022-11-14 22:06:22
*/
@Service
public class ArticleLabelServiceImpl extends ServiceImpl<ArticleLabelMapper, ArticleLabel>
    implements ArticleLabelService{

}




