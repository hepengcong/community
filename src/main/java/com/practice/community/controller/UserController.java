package com.practice.community.controller;

import com.practice.community.Enum.ResultCode;

import com.practice.community.service.UserService;
import com.practice.community.shiro.bean.User;
import com.practice.community.util.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/signUp")
    public JsonResult signUp(@RequestBody User user){
        userService.signUp(user);
        return new JsonResult(true, ResultCode.SUCCESS);
    }

    @PostMapping("/changePassword")
    public JsonResult changePassword(@RequestBody User user){
        userService.changePassword(user);
        return new JsonResult(true, ResultCode.SUCCESS);
    }


}
