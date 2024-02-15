package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.AdminMapper;
import com.atguigu.ssyx.acl.service.AdminService;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    //1.用户信息分页查询 (条件分页查询)
    @Override
    public IPage<Admin> getPageList(Page<Admin> userPage, AdminQueryVo adminQueryVo) {

        String username = adminQueryVo.getUsername();//.replaceAll("\r|\n", "");
        //1.对用户信息进行非空校验
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(username)){
            //2.封装查询信息
            queryWrapper.eq("username",username);
        }
        //3.调用方法进行查询 返回分页对象
        IPage<Admin> rolePage = baseMapper.selectPage(userPage, queryWrapper);

        return rolePage;
    }


    //2.
}
