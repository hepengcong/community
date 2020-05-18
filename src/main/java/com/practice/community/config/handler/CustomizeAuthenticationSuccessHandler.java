package com.practice.community.config.handler;

import com.alibaba.fastjson.JSON;
import com.practice.community.config.service.vo.SysUserVo;
import com.practice.community.entity.SysUser;
import com.practice.community.service.SysUserService;
import com.practice.community.util.JsonResult;
import com.practice.community.util.ResultTool;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    SysUserService sysUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SysUserVo user = (SysUserVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = sysUserService.findByName(user.getUsername());
        sysUser.setLastLoginTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateUser(sysUser.getId());
        sysUserService.update(sysUser);
        JsonResult result = ResultTool.success();
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));

    }
}


