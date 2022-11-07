package com.pengjl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengjl.entity.Article;
import com.pengjl.vo.ListTagVo;

import java.util.List;

/**
 * 文章表(Article)表数据库访问层
 *
 * @author pengjianglin
 * @since 2022-10-30 01:11:06
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<ListTagVo> selectTagListByArticleId(Long id);

}

