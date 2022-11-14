package com.sxu.xyp.service;

import com.sxu.xyp.model.domain.Labels;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86187
* @description 针对表【labels(标签表)】的数据库操作Service
* @createDate 2022-11-11 01:08:22
*/
public interface LabelsService extends IService<Labels> {
    int addLabel(String label);
}
