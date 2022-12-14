package com.sxu.xyp.controller;

import cn.hutool.core.util.StrUtil;
import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ErrorCode;
import com.sxu.xyp.common.ResultUtil;
import com.sxu.xyp.exception.BusinessException;
import com.sxu.xyp.model.dto.UserDTO;
import com.sxu.xyp.model.params.article.AddArticleParams;
import com.sxu.xyp.model.domain.Articles;
import com.sxu.xyp.model.params.article.ArticleParam;
import com.sxu.xyp.model.params.article.UpdateArticleParams;
import com.sxu.xyp.model.params.label.LableParams;
import com.sxu.xyp.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: TODO
 * @author: author
 * @date: 2022/11/15 9:59
 */
@Api(value = "首页", tags = "首页展示的相关接口")
@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class ArticleController {

    @Resource
    ArticlesService articlesService;

    @Resource
    UserService userService;

    @Resource
    FavortiesService favortiesService;

    @ApiOperation(value = "列出我的帖子")
    @PostMapping("/listMyArticles")
    public BaseResponse<List<ArticleParam>> myArticles(HttpServletRequest request) {
        return ResultUtil.success(articlesService.listMyArticles(request));
    }

    @ApiOperation(value = "列出我的收藏")
    @PostMapping("/listCollect")
    public BaseResponse<List<ArticleParam>> myCollect(HttpServletRequest request) {
        return ResultUtil.success(articlesService.listCollect(request));
    }


    @ApiOperation(value = "列出所有帖子")
    @PostMapping("/list")
    public BaseResponse<List<ArticleParam>> list(HttpServletRequest request) {
        return ResultUtil.success(articlesService.listAll(request));
    }

    @ApiOperation(value = "点击帖子显示详情")
    @PostMapping("/detail")
    public BaseResponse<ArticleParam> detail(@RequestParam Long articleId, HttpServletRequest request) {
        return ResultUtil.success(articlesService.detail(articleId, request));
    }

    @ApiOperation(value = "添加帖子")
    @PostMapping("/add")
    public BaseResponse<Articles> add(@RequestBody AddArticleParams addArticleParams, HttpServletRequest request) {
        Long articleId = articlesService.add(addArticleParams, userService.toUserDTO(request));
        Articles article = articlesService.getById(articleId);
        return ResultUtil.success(article);
    }


    @ApiOperation(value = "删除帖子")
    @DeleteMapping("/remove")
    public BaseResponse<Boolean> remove(@RequestParam Long articleId, HttpServletRequest request) {
        return ResultUtil.success(articlesService.delete(articleId, request));
    }

    @ApiOperation(value = "更新帖子")
    @PostMapping("/update")
    public BaseResponse<Articles> update(@RequestBody UpdateArticleParams updateArticleParams, HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (StrUtil.isBlank(token)) {
            throw new BusinessException(ErrorCode.LOGIN_ERROR, "请登陆后修改自己的帖子");
        }
        return ResultUtil.success(articlesService.updateArticle(updateArticleParams));
    }

    @ApiOperation(value = "收藏帖子")
    @PostMapping("/collect")
    public BaseResponse<Boolean> collect(@RequestParam Long articleId, HttpServletRequest request) {
        return ResultUtil.success(favortiesService.collect(articleId, request));
    }

    @ApiOperation(value = "取消收藏")
    @PostMapping("/unCollect")
    public BaseResponse<Boolean> unCollect(@RequestParam Long articleId, HttpServletRequest request) {
        return ResultUtil.success(favortiesService.UnCollect(articleId, request));
    }


    @ApiOperation(value = "指定用户id收藏的帖子")
    @GetMapping("/findCollectById")
    public BaseResponse<List<ArticleParam>> findFavoriteArticlesByUserId(@RequestParam Long userId,HttpServletRequest request) {
        return ResultUtil.success(articlesService.findFavoriteArticlesByUserId(userId, request));
    }

    @ApiOperation(value = "通过标签查找文章")
    @PostMapping("/findArticleByLabel")
    public BaseResponse<List<ArticleParam>> findArticleByLabel(@RequestBody LableParams lableParams, HttpServletRequest request) {
        return ResultUtil.success(articlesService.findArticleByLabel(lableParams, request));
    }

    @ApiOperation(value = "指定用户id的个人信息")
    @GetMapping("/findUser")
    public BaseResponse<UserDTO> findUserByUserId(@RequestParam Long userId, HttpServletRequest request) {
        if (userId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        UserDTO userDTO = articlesService.findUserByUserId(userId, request);
        return ResultUtil.success(userDTO);
    }

    @GetMapping("/findArticle")
    public BaseResponse<List<ArticleParam>> findArticlesByUserId(@RequestParam Long userId, HttpServletRequest request) {
        if (userId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<ArticleParam> articlesByUserId = articlesService.findArticlesByUserId(userId, request);
        return ResultUtil.success(articlesByUserId);
    }

    @GetMapping("/search")
    public BaseResponse<Articles> search() {
        return ResultUtil.success(null);
    }
}
