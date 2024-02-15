package com.atguigu.ssyx.acl.utils;

import com.atguigu.ssyx.acl.service.PermissionService;
import com.atguigu.ssyx.model.acl.Permission;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PermissionHelper {

    @Autowired
    private PermissionService permissionService;

    public static List<Permission> buildPermission(List<Permission> allList){

        //1.创建最终数据封装list集合
        List<Permission> trees = new ArrayList<>();

        //2.遍历所有菜单集合list拿到第一层数据
        for (Permission permission : allList) {
            //2.1.判断是否是第一层
            if (permission.getPid()==0){
                permission.setLevel(1);//设置第一层
                //调用方法,从第一层递归往下找
                trees.add(findChildren(permission,allList));
            }
        }
        return trees;
    }




    /**
     * TODO 递归往下找所有菜单
     * @param permission 当前节点菜单信息
     * @param allList    所有菜单信息
     * @return
     */
    private static Permission findChildren(Permission permission,
                                           List<Permission> allList) {

        permission.setChildren(new ArrayList<Permission>());
        //遍历所有菜单信息
        for (Permission it : allList) {
            //判断当前节点的id与pid是否一样
            if (permission.getId().longValue()==it.getPid().longValue()){

                //有一样的代表该节点还有字节的层级+1
                int level = permission.getLevel()+1;
                it.setLevel(level);

                if (permission.getChildren()==null){
                    permission.setChildren(new ArrayList<Permission>());
                }
                //递归封装下一层数据 Children代表下一层数据
                permission.getChildren().add(findChildren(it,allList));
            }
        }
        return permission;
    }
}
