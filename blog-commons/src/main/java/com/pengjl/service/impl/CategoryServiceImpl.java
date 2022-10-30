package com.pengjl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjl.entity.Article;
import com.pengjl.mapper.CategoryMapper;
import com.pengjl.entity.Category;
import com.pengjl.service.ArticleService;
import com.pengjl.service.CategoryService;
import com.pengjl.utils.BeanCopyUtils;
import com.pengjl.utils.ResponseResult;
import com.pengjl.utils.SystemConstants;
import com.pengjl.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author pengjianglin
 * @since 2022-10-30 12:37:26
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        //查询文章表 状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> list = articleService.list(articleWrapper);
        //获取文章的分类id 去重
        Set<Long> collect = list.stream().map(Article::getCategoryId).collect(Collectors.toSet());
        //得到分类id集合 查询分类表
        List<Category> categories = listByIds(collect);
        categories = categories.stream()
                            .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus())).
                        collect(Collectors.toList());
        //封装VO
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}

