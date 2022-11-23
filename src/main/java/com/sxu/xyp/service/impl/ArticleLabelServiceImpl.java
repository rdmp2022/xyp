package com.sxu.xyp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.model.domain.ArticleLabel;
import com.sxu.xyp.model.domain.Labels;
import com.sxu.xyp.model.params.label.LabelParam;
import com.sxu.xyp.service.ArticleLabelService;
import com.sxu.xyp.mapper.ArticleLabelMapper;
import com.sxu.xyp.service.LabelsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 86187
 * @description 针对表【article_label(帖子_标签表)】的数据库操作Service实现
 * @createDate 2022-11-14 22:06:22
 */
@Service
public class ArticleLabelServiceImpl extends ServiceImpl<ArticleLabelMapper, ArticleLabel> implements ArticleLabelService {

    @Resource
    ArticleLabelMapper articleLabelMapper;

    @Resource
    LabelsService labelsService;

    @Override
    public List<String> getAllLabelID(Long id) {
        List<String> list = new ArrayList<>();
        List<ArticleLabel> articleLabels = articleLabelMapper.selectBatchIds(Collections.singletonList(id));
        for (ArticleLabel articleLabel : articleLabels) {
            QueryWrapper<Labels> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("label_id", articleLabel.getLabelId());
            Labels label = labelsService.getOne(queryWrapper);
            list.add(label.getLabelName());
        }
        return list;
    }

    @Override
    public boolean deleteById(Long articleId) {
        articleLabelMapper.delete(new QueryWrapper<ArticleLabel>().eq("article_id", articleId));
        return true;
    }

    @Override
    public List<Long> getArticleId(List<String> labelParams) {

        HashSet<Long> set = new HashSet<>();
        for (String labelParam : labelParams) {
            List<ArticleLabel> articleIds = new ArrayList<>();
            Long id = Long.parseLong(labelParam);
            if (id == 0) {
                articleIds = articleLabelMapper.selectList(null);
            } else {
                articleIds = articleLabelMapper.selectList(new QueryWrapper<ArticleLabel>().eq("label_id", id));
            }
            for (ArticleLabel articleId : articleIds) {
                set.add(articleId.getArticleId());
            }
        }
        return new ArrayList<Long>(set);
    }


}




