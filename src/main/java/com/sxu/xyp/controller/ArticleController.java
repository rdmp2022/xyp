package com.sxu.xyp.controller;

import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ResultUtil;
import com.sxu.xyp.model.domain.Article.AddArticle;
import com.sxu.xyp.model.domain.Article.Articles;
import com.sxu.xyp.model.domain.Article.OpenArticles;
import com.sxu.xyp.model.domain.ArticleLabel;
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

    @GetMapping("/add")
    public BaseResponse<Articles> add(@RequestBody AddArticle addArticle, HttpServletRequest request) {
        Long articleId = articlesService.add(addArticle, userService.toUserDTO(request));
        Articles article = articlesService.getById(articleId);
        return ResultUtil.success(article);
    }


    @GetMapping("/remove")
    public BaseResponse<List<OpenArticles>> remove() {

        return ResultUtil.success(null);
    }

    @GetMapping("/update")
    public BaseResponse<List<OpenArticles>> update() {

        return ResultUtil.success(null);
    }



    @GetMapping("/list")
    public BaseResponse<List<OpenArticles>> list() {

        return ResultUtil.success(null);
    }

    @GetMapping("/detail")
    public BaseResponse<List<OpenArticles>> detail() {

        return ResultUtil.success(null);
    }

    @GetMapping("/search")
    public BaseResponse<List<OpenArticles>> search() {

        return ResultUtil.success(null);
    }


}
