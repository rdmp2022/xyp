package com.sxu.xyp.controller;


import com.sxu.xyp.common.BaseResponse;
import com.sxu.xyp.common.ResultUtil;

import com.sxu.xyp.model.domain.Banner;
import com.sxu.xyp.model.params.user.UserIndexParam;
import com.sxu.xyp.service.BannerService;
import com.sxu.xyp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: TODO
 * @author: author
 * @date: 2022/11/13 16:28
 */

@Api(value = "首页",tags = "首页展示的相关接口")
@RestController
@CrossOrigin("*")
@RequestMapping("/home")
public class IndexController {

    @Resource
    BannerService bannerService;

    @Resource
    UserService userService;

    @ApiOperation(value = "获取首页轮播图")
    @GetMapping("/banner")
    public BaseResponse<List<Banner>> banner(){
        List<Banner> list = bannerService.list();
        return ResultUtil.success(list);
    }

    @ApiOperation(value = "热榜")
    @GetMapping("/hot")
    public BaseResponse<Object> hot(){

        return ResultUtil.success(null);
    }

    @ApiOperation(value = "实时")
    @GetMapping("/realtime")
    public BaseResponse<Object> realtime(){

        return ResultUtil.success(null);
    }

    @ApiOperation(value = "推荐")
    @GetMapping("/recommend")
    public BaseResponse<Object> recommend(){

        return ResultUtil.success(null);
    }


    @ApiOperation(value = "作者榜单")
    @GetMapping("/userList")
    public BaseResponse<List<UserIndexParam>> userList(){
        // 文章收藏数加起来

        return ResultUtil.success(null);
    }
}
