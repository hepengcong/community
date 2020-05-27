package com.practice.community.service;

import com.practice.community.entity.SysPermission;
import com.practice.community.entity.SysRequest;
import com.practice.community.mapper.SysPermissionMapper;
import com.practice.community.mapper.SysRequestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysPermissionService {
    @Resource
    SysPermissionMapper sysPermissionMapper;
    @Resource
    SysRequestMapper sysRequestMapper;
    public SysPermission findById(Integer id){
        return sysPermissionMapper.findById(id);
    }


    public List<SysPermission> selectListByPath(String requestUrl) {
        List<SysRequest> list=sysRequestMapper.findByUrl(requestUrl);
        List<SysPermission> sys=new ArrayList<>();
        for (SysRequest sysRequest : list) {
            sys.add(findById(sysRequest.getId()));
        }
        return sys;
    }
}
