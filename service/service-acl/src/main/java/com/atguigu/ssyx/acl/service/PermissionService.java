package com.atguigu.ssyx.acl.service;

import com.atguigu.ssyx.model.acl.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface PermissionService extends IService<Permission> {
    List<Permission> getPermissionList();

    void removePermission(Long id);


    void doAssignById(Long roleId, Long[] permissionIds);

    Map<String, Object> getPermissionById(Long roleId);

}
