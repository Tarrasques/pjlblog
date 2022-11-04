package com.pengjl.controller;

import com.pengjl.entity.Comment;
import com.pengjl.service.CommentService;
import com.pengjl.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList() {
        return commentService.commentList();
    }

    @GetMapping("/linkCommentList")
    public ResponseResult commentLinkList() {
        return commentService.commentList();
    }
    @PostMapping()
    public ResponseResult addComment(@RequestBody Comment comment) {
        return  commentService.addComment(comment);
    }
}
