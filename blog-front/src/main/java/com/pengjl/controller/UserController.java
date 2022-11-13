package com.pengjl.controller;

import com.pengjl.annotation.SysLog;
import com.pengjl.entity.User;
import com.pengjl.service.UserService;
import com.pengjl.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo() {
            return  userService.userInfo();
    }
    @PutMapping("/userInfo")
    @SysLog(bussinessName = "更新个人信息")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        userService.updateById(user);
        return ResponseResult.okResult();
    }
}
