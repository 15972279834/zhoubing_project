package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.PermissionMapper;
import com.atguigu.ssyx.acl.mapper.RolePermissionMapper;
import com.atguigu.ssyx.acl.service.PermissionService;
import com.atguigu.ssyx.acl.utils.PermissionHelper;
import com.atguigu.ssyx.model.acl.Permission;
import com.atguigu.ssyx.model.acl.RolePermission;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionService permissionService;



    /**
     * 1.查询所有菜单
     * @return
     */
    @Override
    public List<Permission> getPermissionList() {

        //1.查询出所有的菜单
        List<Permission> allPermissionList = baseMapper.selectList(null);
        //2.转换成要求的数据格式
        List<Permission> result = PermissionHelper.buildPermission(allPermissionList);
        return result;
    }

    /**
     * 2.递归删除
     * @param id
     */
    @Override
    public void removePermission(Long id) {

        List<Long> IdList = new ArrayList<>();
        IdList.add(id);

        //递归添加子菜单id在集合中
        this.getAllPermissionId(id,IdList);

        baseMapper.deleteBatchIds(IdList);
    }

    /**
     * 3.查询用户菜单
     * @param roleId 用户id
     * @return
     */
    @Override
    public Map<String, Object> getPermissionById(Long roleId) {
        //1.获取所有权限
        List<Permission> permissionAllList = permissionService.getPermissionList();

        //2.获取角色已经分配的菜单
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        List<RolePermission> permissionList = rolePermissionMapper.selectList(wrapper);


        //3.将数据封装到map 返回 TODO 前端的名字不对要改
        Map<String, Object> rolePermissionMap = new HashMap<>();
        rolePermissionMap.put("records",permissionAllList);
        rolePermissionMap.put("total",permissionList);
        return rolePermissionMap;
    }

    /**
     * 4.给用户添加新菜单
     * @param roleId        用户id
     * @param permissionIds 菜单ids
     */
    @Override
    public void doAssignById(Long roleId, Long[] permissionIds) {
        //1删除以前的权限
        rolePermissionMapper.deleteById(roleId);
        //2.添加新的权限
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        for (Long permissionId : permissionIds) {
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);
        }

    }


    //TODO 递归添加子菜单id在集合中
    private void getAllPermissionId(long id,List<Long> IdList) {

        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("pid",id);
        List<Permission> permissionList = baseMapper.selectList(wrapper);

        for (Permission permission : permissionList) {
            IdList.add(permission.getId());

            //递归
            this.getAllPermissionId(permission.getId(),IdList);
        }
    }

}
