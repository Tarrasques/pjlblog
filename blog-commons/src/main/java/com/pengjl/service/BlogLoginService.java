package com.pengjl.service;

import com.pengjl.entity.User;
import com.pengjl.utils.ResponseResult;

public interface BlogLoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}