package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.AdminRoleMapper;
import com.atguigu.ssyx.acl.service.AdminRoleService;
import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.model.acl.AdminRole;
import com.atguigu.ssyx.model.acl.Role;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AdminRoleServicelmpl extends ServiceImpl<AdminRoleMapper,AdminRole> implements AdminRoleService {


    @Autowired
    private RoleService roleService;

    /**
     * 1.获取某个用户的所有角色
     * @param adminId
     * @return
     */
    @Override
    public Map<String, Object> getRoleToId(Long adminId) {

        //1.获取所有角色
        List<Role> roleList = roleService.getRoleList();

        //2.根据用户Id查询用户分配的角色列表
        QueryWrapper<AdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_id",adminId);
        List<AdminRole> adminRoles = baseMapper.selectList(queryWrapper);

        //3.将数据封装到map 返回
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("allRolesList",roleList);
        roleMap.put("assignRoles",adminRoles);
        return roleMap;
    }

    /**
     * 2.给某个用户分配角色
     * @param adminId
     * @param roleIds
     */
    @Override
    public void saveUserRoleRealtionShip(Long adminId, Long[] roleIds) {

        //1.删除以前的角色
        QueryWrapper<AdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_id",adminId);
        baseMapper.delete(queryWrapper);

        //2.添加新角色
        AdminRole adminRole = new AdminRole();
        adminRole.setAdminId(adminId);

        //3.循环添加新角色
        for (Long roleId : roleIds) {
            adminRole.setRoleId(roleId);
            baseMapper.insert(adminRole);
        }

    }
}
