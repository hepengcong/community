package com.practice.community.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        UpdateWrapper<SysUser> wrapper=new UpdateWrapper<>();
        wrapper.eq("account",sysUser.getAccount());
        sysUserMapper.update(sysUser,wrapper);
    }
}
