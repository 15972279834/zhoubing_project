package com.atguigu.ssyx.acl.controller;


import com.atguigu.ssyx.acl.service.AdminRoleService;
import com.atguigu.ssyx.acl.service.AdminService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.model.acl.AdminRole;
import com.atguigu.ssyx.model.user.User;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@Api(tags = "用户接口")
@RestController
@RequestMapping("/admin/acl/user")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRoleService adminRoleService;

    @ApiOperation("1.用户信息分页查询")
    @GetMapping("{current}/{limit}")
    public Result getPageList(@PathVariable Long current,
                              @PathVariable Long limit,
                              AdminQueryVo adminQueryVo){

        //1.封装分页对象
        Page<Admin> userPage = new Page<>(current,limit);
        //3.调用方法 返回分页对象
        IPage<Admin> pageMadel= adminService.getPageList(userPage,adminQueryVo);
        return Result.ok(pageMadel);
    }

    @ApiOperation("2.根据Id获取用户信息")
    @GetMapping("get/{id}")
    public Result getById(@PathVariable Long id){
        Admin user = adminService.getById(id);
        return Result.ok(user);
    }


    @ApiOperation("3.添加用户")
    @PostMapping("save")
    public Result save(@RequestBody Admin admin){
        adminService.save(admin);
        return Result.ok(null);
    }

    @ApiOperation("4.修改用户")
    @PutMapping("update")
    public Result update(@RequestBody Admin admin){
        adminService.updateById(admin);
        return Result.ok(null);
    }

    @ApiOperation("5.根据ID删除用户")
    @DeleteMapping("remove/{id}")
    public Result getRoles(@PathVariable Long id){
        adminService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation("6.批量删除多个用户")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        adminService.removeByIds(ids);
        return Result.ok(null);
    }


    @ApiOperation("7.根据用户获取角色信息")
    @GetMapping("toAssign/{adminId}")
    public Result batchRemove(@PathVariable Long adminId){
        Map<String, Object> roleMap= adminRoleService.getRoleToId(adminId);
        return Result.ok(roleMap);
    }


    @ApiOperation("8.给用户分配角色")
    @PostMapping("doAssign")
    public Result doAssign(@RequestParam Long adminId,
                           @RequestParam Long[] roleId){
        adminRoleService.saveUserRoleRealtionShip(adminId,roleId);
        return Result.ok(null);
    }
}
