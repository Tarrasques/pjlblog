package com.pengjl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjl.entity.Article;
import com.pengjl.entity.Comment;
import com.pengjl.mapper.CommentMapper;
import com.pengjl.service.CommentService;
import com.pengjl.utils.MyRequestUtil;
import com.pengjl.utils.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author pengjianglin
 * @since 2022-11-03 23:43:53
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public ResponseResult commentList() {
        Integer pageNum = MyRequestUtil.getRequest().getPageNum();
        Integer pageSize = MyRequestUtil.getRequest().getPageSize();
        String aid = MyRequestUtil.getRequest().getValue("aid");
        Long articleId = Long.valueOf(aid);

        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
       // articleLambdaQueryWrapper.eq();
        Page<Article> page = new Page<>(pageNum,pageSize);
        return null;
    }
}

