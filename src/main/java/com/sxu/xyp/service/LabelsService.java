package com.sxu.xyp.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sxu.xyp.model.domain.Labels;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86187
* @description 针对表【labels(标签表)】的数据库操作Service
* @createDate 2022-11-14 22:02:17
*/
public interface LabelsService extends IService<Labels> {
    public Long addLabel(String label);

    public Boolean searchLabel(String label);
}
