package com.sxu.xyp.controller;

import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ErrorCode;
import com.sxu.xyp.common.ResultUtil;
import com.sxu.xyp.exception.BusinessException;
import com.sxu.xyp.model.domain.CampusInformation;
import com.sxu.xyp.model.params.CampusInfo.AddCampusInfoParams;
import com.sxu.xyp.model.params.CampusInfo.UpdateCampusInfoParams;
import com.sxu.xyp.service.CampusInformationService;
import com.sxu.xyp.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "校园资讯接口",tags = "校园资讯相关")
@RestController
@CrossOrigin("*")
@RequestMapping("/campusInfo")
public class CampusInfoController {

    @Resource
    private CampusInformationService campusInformationService;

    @PostMapping("/addInfo")
    public BaseResponse<Boolean> addInfo(@RequestBody AddCampusInfoParams addCampusInfoParams, HttpServletRequest request){
        if (addCampusInfoParams == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        Boolean result = campusInformationService.addInfo(addCampusInfoParams, request);
        return ResultUtil.success(result);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInfo(@RequestParam Integer id, HttpServletRequest request){
        Boolean result = campusInformationService.deleteCampusInfo(id, request);
        return ResultUtil.success(result);
    }

    @PutMapping("/updateInfo")
    public BaseResponse<Boolean> updateInfo(UpdateCampusInfoParams updateCampusInfoParams, HttpServletRequest request){
        if (updateCampusInfoParams == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        Boolean result = campusInformationService.updateInfoById(updateCampusInfoParams, request);
        return ResultUtil.success(result);
    }

    @GetMapping("/list")
    public BaseResponse<List<CampusInformation>> list(HttpServletRequest request){
        if (request == null){
            throw new BusinessException(ErrorCode.LOGIN_ERROR);
        }
        List<CampusInformation> infoList = campusInformationService.listInfo(request);
        return ResultUtil.success(infoList);
    }


}
