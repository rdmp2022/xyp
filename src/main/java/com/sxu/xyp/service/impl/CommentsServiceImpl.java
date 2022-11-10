package com.sxu.xyp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxu.xyp.model.domain.Comments;
import com.sxu.xyp.service.CommentsService;
import com.sxu.xyp.mapper.CommentsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【comments(评论表)】的数据库操作Service实现
* @createDate 2022-11-11 01:02:10
*/
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments>
    implements CommentsService{

}




