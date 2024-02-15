package com.atguigu.ssyx.acl.controller;


import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api(tags ="角色接口")
@RestController
@RequestMapping("/admin/acl/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    //1.分页查询角色信息 (条件分页查询)
    @ApiOperation("分页查询角色信息")
    @GetMapping("{current}/{limit}")
    public Result getPageList(@PathVariable Long current,
                              @PathVariable Long limit,
                              RoleQueryVo roleQueryVo) {
        //1.创建分页对象
        Page<Role> pageParam = new Page<>(current,limit);
        //2.调用Service方法查询,返回分页对象
       IPage<Role> pageMadel =  roleService.getPageList(pageParam,roleQueryVo);
        return Result.ok(pageMadel);
    }

    //2.根据ID查询角色信息
    @ApiOperation("根据ID查询角色信息")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        Role role= roleService.getRoleToId(id);
        return Result.ok(role);
    }
    //3.添加角色
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody Role role){
        roleService.saveRole(role);
        return Result.ok(null);
    }
    //4.修改角色
    @ApiOperation("修改角色")
    @PutMapping("update")
    public Result update(@RequestBody Role role){
        roleService.updateRole(role);
        return Result.ok(null);
    }
    //5.根据ID删除角色
    @ApiOperation("根据ID删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        roleService.removeRoleToId(id);
        return Result.ok(null);
    }
    //6.批量删除角色
    @ApiOperation("批量删除角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList){
        roleService.batchRemove(idList);
        return Result.ok(null);
    }
}
