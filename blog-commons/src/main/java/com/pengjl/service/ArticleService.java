package com.pengjl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjl.entity.Article;
import com.pengjl.utils.ResponseResult;

/**
 * 文章表(Article)表服务接口
 *
 * @author pengjianglin
 * @since 2022-10-30 01:08:37
 */
public interface ArticleService extends IService<Article> {

    ResponseResult getHotArticleList();

    ResponseResult getArticleList();

    ResponseResult getArticleDetails(Long id);


    ResponseResult updateViewCount(Long id);
}

