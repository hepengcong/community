package com.practice.community.shiro.service.impl;


import com.practice.community.mapper.UserMapper;
import com.practice.community.shiro.bean.User;
import com.practice.community.shiro.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    UserMapper userMapper;
    @Override
    public User getUserByName(String Name) {
        return userMapper.findByName(Name);
    }

}
