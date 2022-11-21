package com.sxu.xyp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.model.domain.Labels;
import com.sxu.xyp.service.LabelsService;
import com.sxu.xyp.mapper.LabelsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 86187
 * @description 针对表【labels(标签表)】的数据库操作Service实现
 * @createDate 2022-11-14 22:02:17
 */
@Service
public class LabelsServiceImpl extends ServiceImpl<LabelsMapper, Labels>
        implements LabelsService{

    @Resource
    LabelsMapper labelsMapper;

    @Override
    public Long addLabel(String label) {
        // 存在返回id
        if (this.searchLabel(label)) {
            return this.getOne(new QueryWrapper<Labels>().eq("label_name",label)).getLabelId();
        }
        // 插入
        Labels labels = new Labels();
        labels.setLabelName(label);
        if (labelsMapper.insert(labels) != 0) {
            return labels.getLabelId();
        }
        // 错误
        return 0L;
    }

    @Override
    public Boolean searchLabel(String label) {
        QueryWrapper<Labels> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("label_name",label);
        int count = this.count(queryWrapper);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Labels> labelList() {
        List<Labels> labels = labelsMapper.selectList(null);
        return labels;
    }

}




