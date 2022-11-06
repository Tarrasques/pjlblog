package com.pengjl.service;

import com.pengjl.entity.User;
import com.pengjl.utils.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}