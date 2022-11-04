package com.pengjl.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjl.entity.Article;
import com.pengjl.entity.Comment;
import com.pengjl.entity.User;
import com.pengjl.mapper.CommentMapper;
import com.pengjl.service.CommentService;
import com.pengjl.service.UserService;
import com.pengjl.utils.*;
import com.pengjl.vo.CommentVo;
import com.pengjl.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author pengjianglin
 * @since 2022-11-03 23:43:53
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList() {
        //文章友链通用查询评论方法
        Integer pageNum = MyRequestUtil.getRequest().getPageNum();
        Integer pageSize = MyRequestUtil.getRequest().getPageSize();
        String aid = MyRequestUtil.getRequest().getValue("articleId");
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
       //说明是文章评论
        if (!StringUtils.isBlank(aid)){
            commentLambdaQueryWrapper.eq(Comment::getType, SystemConstants.COMMENT_ARTICLE);
            commentLambdaQueryWrapper.eq(Comment::getArticleId,Long.valueOf(aid));
        }else {
            commentLambdaQueryWrapper.eq(Comment::getType,SystemConstants.COMMENT_LINK);
        }
        //查询根评论
        commentLambdaQueryWrapper.eq(Comment::getRootId,-1);
        Page<Comment> page = new Page<>(pageNum,pageSize);
        page(page,commentLambdaQueryWrapper);
    
        List<CommentVo> commentVos =toCommentVoList(page.getRecords());

        for (CommentVo commentVo : commentVos) {
                 List<CommentVo> children= getChildren(commentVo.getId());
                 commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new PageVo(commentVos,page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
         save(comment);
        return ResponseResult.okResult();
    }

    /**
     * 根据根评论id查询所对应的子评论集合
     * @param id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> list = list(queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(list);
        return  commentVos;
    }

    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历vo集合

        for (CommentVo commentVo : commentVos) {
            //通过userid查询username
            String nickName = userService.getById(commentVo.getCreateBy()).getUserName();
            commentVo.setUsername(nickName);
            //如果toCommentUserId等于-1，说明不是回复的评论，要判断 ：不等于-1，再进行查询,
            if (commentVo.getToCommentUserId()!=-1){
                String toNickName = userService.getById(commentVo.getToCommentUserId()).getUserName();
                commentVo.setToCommentUserName(toNickName);
            }

        }
        return commentVos;
    }
}

