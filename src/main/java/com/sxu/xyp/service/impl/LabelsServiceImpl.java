package com.sxu.xyp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.model.domain.Labels;
import com.sxu.xyp.service.LabelsService;
import com.sxu.xyp.mapper.LabelsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【labels(标签表)】的数据库操作Service实现
* @createDate 2022-11-11 01:08:22
*/
@Service
public class LabelsServiceImpl extends ServiceImpl<LabelsMapper, Labels>
    implements LabelsService{

}



