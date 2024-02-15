package com.atguigu.ssyx.acl.service;

import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;



public interface RoleService extends IService<Role> {

    IPage<Role> getPageList(Page<Role> pageParam, RoleQueryVo roleQueryVo);

    Role getRoleToId(Long id);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRoleToId(Long id);

    void batchRemove(List<Long> idList);

    List<Role> getRoleList();

}
