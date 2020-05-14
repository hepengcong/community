package com.practice.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.community.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    @Select("select * from sys_permission where Id=#{Id}")
    SysPermission findById(@Param("Id") Integer Id);
}
