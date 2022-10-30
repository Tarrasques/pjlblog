package com.pengjl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjl.entity.Category;
import com.pengjl.utils.ResponseResult;

/**
 * 分类表(Category)表服务接口
 *
 * @author pengjianglin
 * @since 2022-10-30 12:37:26
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

