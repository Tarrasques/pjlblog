package com.pengjl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjl.entity.ArticleTag;
import com.pengjl.mapper.ArticleTagMapper;
import com.pengjl.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author pengjianglin
 * @since 2022-11-07 13:35:38
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}

