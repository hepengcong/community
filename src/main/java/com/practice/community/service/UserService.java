package com.practice.community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.community.entity.User;
import com.practice.community.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    PasswordEncoder passwordEncoder;

    public void signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        user.setGmtModified(user.getGmtCreate());

        if (userMapper.findByName(user.getName()) == null)
            userMapper.insert(user);
        else throw new RuntimeException();
    }

    public void changePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User select = userMapper.findByName(user.getName());
        if (select != null)
            select.setPassword(user.getPassword());
        else throw new RuntimeException();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", select.getId())
                .eq("name", select.getName());
        userMapper.update(select, wrapper);
    }


}
