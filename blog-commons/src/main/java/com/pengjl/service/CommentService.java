package com.pengjl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjl.entity.Comment;
import com.pengjl.utils.ResponseResult;


/**
 * 评论表(Comment)表服务接口
 *
 * @author pengjianglin
 * @since 2022-11-03 23:43:53
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList();

    ResponseResult addComment(Comment comment);
}

