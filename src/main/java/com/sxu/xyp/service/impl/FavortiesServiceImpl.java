package com.sxu.xyp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.model.domain.Favorties;
import com.sxu.xyp.service.FavortiesService;
import com.sxu.xyp.mapper.FavortiesMapper;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【favorties(用户-收藏表)】的数据库操作Service实现
* @createDate 2022-11-14 21:58:30
*/
@Service
public class FavortiesServiceImpl extends ServiceImpl<FavortiesMapper, Favorties>
    implements FavortiesService{

}




