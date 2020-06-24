package com.practice.community.shiro.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("permissions")
@Data
public class Permissions {
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    private String permissionsName;
}
