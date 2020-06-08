package com.practice.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String password;
    private String name;
    private String token;
    private Timestamp  gmtCreate;
    private Timestamp  gmtModified;
    private String role;
}
