package com.practice.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author helium
 */
@Data
public class SysRequest {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String url;
    private String description;
}
