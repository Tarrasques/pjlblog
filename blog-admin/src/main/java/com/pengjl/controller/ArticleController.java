package com.pengjl.controller;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengjl.entity.Article;
import com.pengjl.service.ArticleService;
import com.pengjl.utils.MyRequestUtil;
import com.pengjl.utils.ResponseResult;
import com.pengjl.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/content/article")
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;


    @GetMapping("/list")
    public ResponseResult list(){
        Page<Article> tagPage = new Page<>(MyRequestUtil.getRequest().getPageNum(), MyRequestUtil.getRequest().getPageSize());
        String title = MyRequestUtil.getRequest().getValue("title");
        String summary = MyRequestUtil.getRequest().getValue("summary");

        articleService.page(tagPage,
                new LambdaQueryWrapper<Article>()
                        .like(StringUtils.isNotBlank(title),Article::getTitle,title)
                        .like(StringUtils.isNotBlank(summary),Article::getSummary,summary)
        );
        return ResponseResult.okResult(new PageVo(tagPage.getRecords(), tagPage.getTotal()));
    }
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable("id") Long id) {
        return articleService.getarticleById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteById(@PathVariable("id") Long... id) {
        Long[] ids = id;
        return ResponseResult.okResult(articleService.removeByIds(Arrays.asList(ids)));
    }
    @PutMapping()
    public ResponseResult update(@RequestBody JSONObject jsonObject) {
        return articleService.updateArticle(jsonObject);
    }
    @PostMapping()
    public ResponseResult add(@RequestBody JSONObject jsonObject) {
        return articleService.addArticle(jsonObject);
    }
}
