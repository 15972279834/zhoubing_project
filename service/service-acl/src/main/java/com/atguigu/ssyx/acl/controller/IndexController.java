package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


@Api(tags = "登录接口")
@CrossOrigin     //跨域
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {

    //1.login 登录
    @ApiOperation(("登录"))
    @PostMapping("login")
    public Result login(){

        //返回token
        Map<String, String> map = new HashMap<>();
        map.put("token","token-admin");
        return Result.ok(map);
    }
    //2.getInfo 获取信息

    @ApiOperation(("获取信息"))
    @GetMapping("info")
    public Result getInfo(){

        Map<String,Object> map = new HashMap<>();
        map.put("name","周冰");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }
    //3.logOut  退出
    @ApiOperation(("退出"))
    @PostMapping("logout")
    public Result logOut(){
        return Result.ok(null);
    }
}
