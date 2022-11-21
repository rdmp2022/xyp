package com.sxu.xyp.controller;

import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ResultUtil;
import com.sxu.xyp.model.domain.Labels;
import com.sxu.xyp.model.params.ArticleParam;
import com.sxu.xyp.service.LabelsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: TODO
 * @author: author
 * @date: 2022/11/21 15:40
 */
@Api(value = "标签",tags = "标签的相关接口")
@RestController
@CrossOrigin("*")
@RequestMapping("/label")
public class LabelController {

    @Resource
    LabelsService labelsService;

    @ApiOperation(value = "展示所有标签")
    @PostMapping("/list")
    public BaseResponse<List<Labels>> list(HttpServletRequest request) {
        return ResultUtil.success(labelsService.labelList());
    }


}
