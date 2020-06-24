package com.practice.community.shiro.service;


import com.practice.community.shiro.bean.User;

public interface LoginService{

    User getUserByName(String userName);
}
