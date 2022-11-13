package com.pengjl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjl.entity.User;
import com.pengjl.utils.ResponseResult;


/**
 * 用户表(User)表服务接口
 *
 * @author pengjianglin
 * @since 2022-11-04 16:51:20
 */

public interface UserService extends IService<User> {

    ResponseResult userInfo();
}

