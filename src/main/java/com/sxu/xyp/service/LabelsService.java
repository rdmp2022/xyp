package com.sxu.xyp.service;

import com.sxu.xyp.model.domain.Labels;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxu.xyp.model.params.label.LabelParam;

import java.util.List;

/**
* @author 86187
* @description 针对表【labels(标签表)】的数据库操作Service
* @createDate 2022-11-14 22:02:17
*/
public interface LabelsService extends IService<Labels> {
    /*
    *  Labels中插入 label,返回 labels_id
    */
    Long addLabel(String label);

    Boolean searchLabel(String label);

    List<LabelParam> labelList();

}
