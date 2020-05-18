package com.practice.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.community.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    @Select("select * from sys_permission where Id=#{Id}")

    SysPermission findById(@Param("Id") Integer Id);
}
