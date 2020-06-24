package com.practice.community.shiro.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
@TableName("user")
public class User {
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    private String name;
    private String password;
    private Timestamp gmtCreate;
    private Timestamp  gmtModified;
    private Set<Role> roles;
}
