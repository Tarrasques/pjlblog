package com.pengjl.utils;

import com.pengjl.entity.User;
import com.pengjl.vo.LoginUser;
import com.pengjl.vo.UserInfoVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class SecurityUtil {

    private SecurityUtil(){

    }
    /**
     *获取当前登录用户信息
     * @return User
     */
    public static User  getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User user = loginUser.getUser();
        return user;
    }

    /**
     * 获取当前登录用户id
     * @return userid
     */
    public static Long getUserId() {
        return getUserInfo().getId();
    }

}
