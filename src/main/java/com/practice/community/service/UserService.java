package com.practice.community.service;

import com.practice.community.dto.GithubUser;
import com.practice.community.mapper.UserMapper;
import com.practice.community.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public void insert(GithubUser githubUser,User user){
        String token= UUID.randomUUID().toString();
        user.setToken(token);
        user.setName(githubUser.getName());
        user.setAccountId(String.valueOf(githubUser.getId()));
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        user.setAvatarUrl(githubUser.getAvatarUrl());
        userMapper.insert(user);
    }

    public User findByToken(String token){
       return userMapper.findByToken(token);
    }

}
