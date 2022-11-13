package com.pengjl.controller;

import com.pengjl.entity.User;
import com.pengjl.service.BlogLoginService;
import com.pengjl.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return blogLoginService.login(user);
    }
    @PostMapping ("/logout")
    public ResponseResult logout() {
        return blogLoginService.logout();
    }
}
