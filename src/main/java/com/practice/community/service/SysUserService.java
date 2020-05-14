package com.practice.community.service;

import com.practice.community.entity.SysUser;
import com.practice.community.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserService {
    @Resource
    SysUserMapper sysUserMapper;
    public SysUser findByName(String username){
        return sysUserMapper.findByName(username);
    }

    public void update(SysUser sysUser) {
    }
}
