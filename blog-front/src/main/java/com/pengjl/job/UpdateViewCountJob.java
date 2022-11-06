package com.pengjl.job;

import com.pengjl.entity.Article;
import com.pengjl.service.ArticleService;
import com.pengjl.utils.RedisCache;
import com.pengjl.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void updateViewCount() {
        //获取redis中浏览量
        Map<String, Integer> cacheMap = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEWCOUNT_REDIS_PREFIX);
        List<Article> articleList = cacheMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        //更新到数据库
        articleService.updateBatchById(articleList);
    }
}
