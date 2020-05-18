package com.practice.community.config.service;

import com.practice.community.config.service.vo.SysUserVo;
import com.practice.community.entity.SysPermission;
import com.practice.community.entity.SysUser;
import com.practice.community.service.SysPermissionService;
import com.practice.community.service.SysRolePermissionRelationService;
import com.practice.community.service.SysUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    SysUserService sysUserService;
    @Resource
    SysRolePermissionRelationService sysRolePermissionRelationService;
    @Resource
    SysPermissionService sysPermissionService;
    @Override
    public SysUserVo loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (userName==null||"".equals(userName))
            throw new RuntimeException("user not exist");
        SysUser sysUser= sysUserService.findByName(userName);
        if (sysUser==null)
            throw new RuntimeException("sysUser not exist");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (sysUser!=null) {
            Integer[] permissionId=sysRolePermissionRelationService.findAllPermissionById(sysUser.getId());
            List<SysPermission> sysPermissions = new ArrayList<>();
            for (Integer integer : permissionId) {
                sysPermissions.add(sysPermissionService.findById(integer));
            }
            // 声明用户授权
            sysPermissions.forEach(sysPermission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
                grantedAuthorities.add(grantedAuthority);
            });
        }

        return new SysUserVo(sysUser.getId(),sysUser.getAccount(), sysUser.getPassword(),sysUser.getAccountNonExpired(),sysUser.getAccountNonLocked(),sysUser.getCredentialsNonExpired(),sysUser.getEnabled(),grantedAuthorities);
    }
}
