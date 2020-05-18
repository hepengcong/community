package com.practice.community.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ReplyDTO {
    private Integer id;
    private Integer creatorId;
    private Integer questionId;
    private String description;
    private Date createTime;
    private Integer page = 1;
    private Integer size = 10;
}
