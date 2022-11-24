package com.sxu.xyp.service;

import com.sxu.xyp.model.domain.CampusInformation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxu.xyp.model.params.CampusInfo.AddCampusInfoParams;
import com.sxu.xyp.model.params.CampusInfo.UpdateCampusInfoParams;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author Warmly
* @description 针对表【campus_information】的数据库操作Service
* @createDate 2022-11-24 18:20:52
*/
public interface CampusInformationService extends IService<CampusInformation> {

    Boolean addInfo(AddCampusInfoParams addCampusInfoParams, HttpServletRequest request);

    Boolean deleteCampusInfo(Integer id, HttpServletRequest request);

    Boolean updateInfoById(UpdateCampusInfoParams updateCampusInfoParams, HttpServletRequest request);

    List<CampusInformation> listInfo(HttpServletRequest request);
}
