package com.practice.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author hupeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUser {

    private  Integer id;

    private String userName;

    private String browser;

    private String ip;

    private String address;

    private String key;

    private Date loginTime;
}
