package com.sxu.xyp.controller;

import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ErrorCode;
import com.sxu.xyp.common.ResultUtil;
import com.sxu.xyp.exception.BusinessException;
import com.sxu.xyp.model.params.AddArticleParams;
import com.sxu.xyp.model.domain.Articles;
import com.sxu.xyp.service.*;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: TODO
 * @author: author
 * @date: 2022/11/15 9:59
 */
@Api(value = "首页",tags = "首页展示的相关接口")
@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class ArticleController {

    @Resource
    ArticlesService articlesService;

    @Resource
    UserService userService;

    @Resource
    ArticleLabelService articleLabelService;

    @Resource
    CommentsService commentsService;

    @Resource
    LabelsService labelsService;

    @PostMapping("/add")
    public BaseResponse<Articles> add(@RequestBody AddArticleParams addArticleParams, HttpServletRequest request) {
        Long articleId = articlesService.add(addArticleParams, userService.toUserDTO(request));
        Articles article = articlesService.getById(articleId);
        return ResultUtil.success(article);
    }


    @DeleteMapping("/remove")
    public BaseResponse<Boolean> remove(@RequestParam Long articleId) {
        if (!articlesService.remove(articleId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtil.success(true);
    }

    @GetMapping("/update")
    public BaseResponse<Articles> list() {
        return null;
    }



    @GetMapping("/detail")
    public BaseResponse<Articles> detail() {

        return ResultUtil.success(null);
    }

    @GetMapping("/search")
    public BaseResponse<Articles> search() {

        return ResultUtil.success(null);
    }


}
