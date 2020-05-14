package com.practice.community.entity;

import lombok.Data;

@Data

public class SysPermission {
    private Integer id;
    //权限code
    private String permissionCode;
    //权限名
    private String permissionName;
}
