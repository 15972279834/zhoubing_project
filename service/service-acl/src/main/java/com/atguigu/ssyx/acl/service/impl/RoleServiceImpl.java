package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.RoleMapper;
import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {



    @Override
    public IPage<Role> getPageList(Page<Role> pageParam, RoleQueryVo roleQueryVo) {

        QueryWrapper<Role> wrapper = new QueryWrapper<>();

        //判断条件是否为空 封装条件
        if (!StringUtils.isEmpty(roleQueryVo.getRoleName())){
            wrapper.eq("role_name", roleQueryVo.getRoleName());
        }
        //调用方法实现分页查询
        IPage<Role> rolePage = baseMapper.selectPage(pageParam, wrapper);

        return rolePage;
    }

    @Override
    public Role getRoleToId(Long id) {
        Role role = baseMapper.selectById(id);
        return role;
    }

    @Override
    public void saveRole(Role role) {
        baseMapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
      baseMapper.updateById(role);
    }

    @Override
    public void removeRoleToId(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void batchRemove(List<Long> idList) {
        baseMapper.deleteBatchIds(idList);
    }

    @Override
    public List<Role> getRoleList() {
        return baseMapper.selectList(null);
    }
}
