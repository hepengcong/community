package com.practice.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practice.community.entity.SysRolePermissionRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysRolePermissionRelationMapper extends BaseMapper<SysRolePermissionRelation> {
    @Select("select permission_id from sys_role_permission_relation where role_id=#{id}")
    Integer[] findAllPermission(@Param("id") Integer id);
}
