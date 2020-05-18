package com.practice.community.entity;

import lombok.Data;

import java.util.Date;

@Data
public class RecentReply {
    private Integer id;
    private Integer creatorId;
    private Integer questionId;
    private String description;
    private Date createTime;
}
