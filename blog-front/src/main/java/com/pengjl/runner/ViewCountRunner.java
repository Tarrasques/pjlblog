package com.pengjl.runner;

import com.pengjl.entity.Article;
import com.pengjl.mapper.ArticleMapper;
import com.pengjl.utils.RedisCache;
import com.pengjl.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Autowired
    RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息 id viewCount
        List<Article> articles = articleMapper.selectList(null);
        //id作为key viewCount作为value
        Map<String, Integer> map = articles.stream().collect(Collectors.toMap(article1 -> article1.getId().toString(), article -> article.getViewCount().intValue()));
        //存入redis
        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEWCOUNT_REDIS_PREFIX,map);
    }
}
