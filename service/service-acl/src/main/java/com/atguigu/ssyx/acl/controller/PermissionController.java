package com.atguigu.ssyx.acl.controller;


import com.atguigu.ssyx.acl.service.PermissionService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Api(tags = "权限接口")
@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //1.查询所有的的菜单
    @ApiOperation("1.查询所有的的菜单")
    @GetMapping
    public Result getPermissionList(){
        List<Permission> list = permissionService.getPermissionList();
        return Result.ok(list);
    }
    //2.新增菜单
    @ApiOperation("2.新增菜单")
    @PostMapping("save")
    public Result addPermission(@RequestBody Permission permission){
        permissionService.save(permission);
        return Result.ok(null);
    }
    //3.修改菜单
    @ApiOperation("3.修改菜单")
    @PutMapping("update")
    public Result updatePermission(@RequestBody Permission permission){
        permissionService.updateById(permission);
        return Result.ok(null);
    }
    //4.递归删除菜单
    @ApiOperation("4.递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result removePermission(@PathVariable Long id) {
        permissionService.removePermission(id);
        return Result.ok(null);
    }


    @ApiOperation("5.查看某个角色的权限列表")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        Map<String, Object> permissionMap = permissionService.getPermissionById(roleId);
        return Result.ok(permissionMap);
    }

    @ApiOperation("6.给某个角色授权")
    @PostMapping("doAssign")
    public Result doAssign(@RequestParam Long roleId,
                           @RequestParam Long[]  permissionIds) {
        permissionService.doAssignById(roleId,permissionIds);
        return Result.ok(null);
    }

}
