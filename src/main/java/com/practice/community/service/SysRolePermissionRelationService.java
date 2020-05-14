package com.practice.community.service;

import com.practice.community.mapper.SysRolePermissionRelationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysRolePermissionRelationService {
    @Resource
    SysRolePermissionRelationMapper sysRolePermissionRelationMapper;

    public Integer[] findAllPermissionById(Integer id) {
        return sysRolePermissionRelationMapper.findAllPermission(id);
    }
}
