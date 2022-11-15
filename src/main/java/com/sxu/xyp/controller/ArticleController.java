package com.sxu.xyp.controller;

import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ResultUtil;
import com.sxu.xyp.model.domain.Article.Articles;
import com.sxu.xyp.model.domain.Article.OpenArticles;
import io.swagger.annotations.Api;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

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

    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/add")
    public BaseResponse<List<OpenArticles>> add() {
        List<Articles> list = articlesService.list();
        return ResultUtil.success(null);
    }


    @GetMapping("/update")
    public BaseResponse<List<OpenArticles>> update() {
        List<Articles> list = articlesService.list();
        return ResultUtil.success(null);
    }

    @GetMapping("/remove")
    public BaseResponse<List<OpenArticles>> remove() {
        List<Articles> list = articlesService.list();
        return ResultUtil.success(null);
    }

    @GetMapping("/list")
    public BaseResponse<List<OpenArticles>> list() {
        List<Articles> list = articlesService.list();
        return ResultUtil.success(null);
    }

    @GetMapping("/detail")
    public BaseResponse<List<OpenArticles>> detail() {
        List<Articles> list = articlesService.list();
        return ResultUtil.success(null);
    }

    @GetMapping("/search")
    public BaseResponse<List<OpenArticles>> search() {
        List<Articles> list = articlesService.list();
        return ResultUtil.success(null);
    }


}
