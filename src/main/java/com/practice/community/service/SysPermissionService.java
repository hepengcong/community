package com.practice.community.service;

import com.practice.community.entity.SysPermission;
import com.practice.community.mapper.SysPermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysPermissionService {
    @Resource
    SysPermissionMapper sysPermissionMapper;

    public SysPermission findById(Integer id){
        return sysPermissionMapper.findById(id);
    }


    public void selectListByPath(String requestUrl) {
    }
}
