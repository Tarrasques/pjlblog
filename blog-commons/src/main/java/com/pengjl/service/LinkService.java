package com.pengjl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjl.entity.Link;
import com.pengjl.utils.ResponseResult;

/**
 * 友链(Link)表服务接口
 *
 * @author pengjianglin
 * @since 2022-10-30 19:56:42
 */
public interface LinkService extends IService<Link> {


    ResponseResult getAllLink();
}

