package com.pengjl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjl.entity.User;
import com.pengjl.mapper.UserMapper;
import com.pengjl.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author pengjianglin
 * @since 2022-11-04 16:51:20
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

