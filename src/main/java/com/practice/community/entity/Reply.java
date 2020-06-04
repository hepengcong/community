package com.practice.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("reply")
public class Reply implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
   private Integer creatorId;
   private Integer questionId;
   private String description;
   private Date createTime;
}
