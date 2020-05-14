package com.practice.community.dto;

import com.practice.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer gmtCreate;
    private Integer gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;

    private Integer page = 1;
    private Integer size = 10;
}
