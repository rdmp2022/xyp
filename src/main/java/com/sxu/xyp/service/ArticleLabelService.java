package com.sxu.xyp.service;

import com.sxu.xyp.model.domain.ArticleLabel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxu.xyp.model.params.label.LabelParam;

import java.util.List;

/**
* @author 86187
* @description 针对表【article_label(帖子_标签表)】的数据库操作Service
* @createDate 2022-11-14 22:06:22
*/
public interface ArticleLabelService extends IService<ArticleLabel> {

    List<String> getAllLabelID(Long id);

    boolean deleteById(Long articleId);

    List<Long> getArticleId(List<String> labelParams);
}
