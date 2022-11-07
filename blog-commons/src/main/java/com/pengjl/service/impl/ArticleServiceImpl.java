package com.pengjl.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjl.entity.ArticleTag;
import com.pengjl.entity.Category;
import com.pengjl.mapper.ArticleMapper;
import com.pengjl.entity.Article;
import com.pengjl.service.ArticleService;
import com.pengjl.service.ArticleTagService;
import com.pengjl.service.CategoryService;
import com.pengjl.utils.*;
import com.pengjl.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 文章表(Article)表服务实现类
 *
 * @author pengjianglin
 * @since 2022-10-30 01:14:19
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleTagService articleTagService;
    @Override
    public ResponseResult getHotArticleList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        wrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page(1,10);
        page(page,wrapper);
        List<Article> records = page.getRecords();
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(records, HotArticleVo.class);
        Map<String, Integer> cacheMap = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEWCOUNT_REDIS_PREFIX);

        hotArticleVos.stream()
                .map(hotArticleVo -> hotArticleVo.setViewCount(cacheMap.get(hotArticleVo.getId().toString()).longValue()))
                .collect(Collectors.toList());
        return ResponseResult.okResult(hotArticleVos);
    }

    @Override
    public ResponseResult getArticleList() {
        //正式发布的，置顶在前
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        wrapper.orderByDesc(Article::getIsTop);
        wrapper.orderByDesc(Article::getUpdateTime);
        //查询参数有可能拓展 todo 目前只有categoryId
        wrapper.eq(CheckUtil.isNumeric(MyRequestUtil.getRequest().getValue("categoryId")),
                    Article::getCategoryId,MyRequestUtil.getRequest().getValue("categoryId"));
        //分页
        Page<Article> page = new Page<>(MyRequestUtil.getRequest().getPageNum(), MyRequestUtil.getRequest().getPageSize());
        page(page,wrapper);
        List<Article> articleList = page.getRecords();
        //分类名称设置
        articleList.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articleList, ArticleListVo.class);

        Map<String, Integer> cacheMap = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEWCOUNT_REDIS_PREFIX);

        articleListVos.stream()
                .map(articleListVo -> articleListVo.setViewCount(cacheMap.get(articleListVo.getId().toString()).longValue()))
                .collect(Collectors.toList());

        return ResponseResult.okResult(new PageVo(articleListVos,page.getTotal()));
    }

    @Override
    public ResponseResult getArticleDetails(Long id) {
        Article article = getById(id);
        //从redis中取得浏览量
        Integer viewCount = redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEWCOUNT_REDIS_PREFIX, id.toString());
        article.setViewCount(viewCount.longValue());

        ArticleDetailsVo articleDetailsVo = BeanCopyUtils.copyBean(article, ArticleDetailsVo.class);
        Category category = categoryService.getById(articleDetailsVo.getCategoryId());
        if (category != null) {
            articleDetailsVo.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(articleDetailsVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应的文章浏览量
        redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_VIEWCOUNT_REDIS_PREFIX,id.toString(),1);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updateArticle(JSONObject jsonObject) {
        Article article = JSONObject.toJavaObject(jsonObject, Article.class);
        article.setId(Long.valueOf(jsonObject.getString("id")));
        //文章表更新
        updateById(article);
        JSONArray tags = jsonObject.getJSONArray("tags");
        //文章标签关联表更新
        // TODO: 2022/11/7 这里先采取删除再保存的方式
        articleTagService.remove(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId,article.getId()));
        //如果tags为空或者为null，表示删除标签，有值才更新
        if (Objects.nonNull(tags) && tags.size()>0) {
            List<Long> tagIds = tags.stream().map(tag -> Long.valueOf((String) tag)).collect(Collectors.toList());
            List<ArticleTag> list = tagIds.stream().map(tagId -> new ArticleTag(article.getId(), tagId)).collect(Collectors.toList());
            articleTagService.saveBatch(list);
        }

        return ResponseResult.okResult();
    }

    /**
     * 后台管理用
     * @param id
     * @return
     */
    @Override
    public ResponseResult getarticleById(Long id) {
        Article article = getById(id);
        List<ListTagVo> listTagVos = baseMapper.selectTagListByArticleId(id);
        List<Long> collect = listTagVos.stream().map(listTagVo -> listTagVo.getId()).collect(Collectors.toList());
        Map<String,Object> map = new HashMap<>();
        map.put("article",article);
        map.put("tags",collect);
        return ResponseResult.okResult(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult addArticle(JSONObject jsonObject) {
        Article article = jsonObject.toJavaObject(Article.class);
        JSONArray tags = jsonObject.getJSONArray("tags");
        save(article);

        if (Objects.nonNull(tags) && tags.size()>0) {
            List<Long> tagIds = tags.stream().map(tag -> Long.valueOf((String) tag)).collect(Collectors.toList());
            List<ArticleTag> list = tagIds.stream().map(tagId -> new ArticleTag(article.getId(), tagId)).collect(Collectors.toList());
            articleTagService.saveBatch(list);
        }
        return ResponseResult.okResult();
    }

}

