package com.pengjl.controller;
import com.pengjl.annotation.SysLog;
import com.pengjl.service.ArticleService;
import com.pengjl.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;



    @GetMapping("/hotArticleList")
    public ResponseResult getHotArticleList() {

        return articleService.getHotArticleList();
    }

    @GetMapping("/articleList")
    @SysLog(bussinessName = "获取首页文章列表")
    public ResponseResult getArticleList() {
        return articleService.getArticleList();
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetails(@PathVariable("id") Long id){
        return articleService.getArticleDetails(id);
    }

    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }
}
