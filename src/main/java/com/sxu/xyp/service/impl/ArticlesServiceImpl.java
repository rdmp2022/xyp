package com.sxu.xyp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.model.domain.Articles;
import com.sxu.xyp.service.ArticlesService;
import com.sxu.xyp.mapper.ArticlesMapper;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【articles(帖子表)】的数据库操作Service实现
* @createDate 2022-11-14 22:06:32
*/
@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Articles>
    implements ArticlesService{

}




