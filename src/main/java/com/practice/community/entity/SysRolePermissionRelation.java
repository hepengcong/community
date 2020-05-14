package com.practice.community.entity;

import lombok.Data;

@Data

public class SysRolePermissionRelation {
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
}