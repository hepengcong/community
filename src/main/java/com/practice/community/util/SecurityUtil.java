package com.practice.community.util;

import cn.hutool.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    public static UserDetails getUserDetails() {
        UserDetails userDetails;
        try {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new RuntimeException("登录状态过期");
        }
        return userDetails;
    }

    public static Integer getUserId(){
        Object obj = getUserDetails();
        JSONObject json = new JSONObject(obj);
        return json.get("id", Integer.class);
    }
}
