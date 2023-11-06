package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //查询所有角色&条件查询  post请求使用注解@RequestBody，将请求体中的json串封装到实体类中来接收
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> roleList = roleService.findAllRole(role);

        return new ResponseResult(true,200,"查询所有角色&条件查询成功",roleList);
    }

    @Autowired
    private MenuService menuService;

    //查询所有的父子菜单信息：属于角色模块
    @RequestMapping("/findSubMenuListByPid")
    public ResponseResult findSubMenuListByPid(){

        //get请求：前端没传递参数
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);// -1 代表查询所有的父级菜单（查询出的结果包含子级菜单）

        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        return new ResponseResult(true,200,"查询所有父子菜单成功",map);
    }

    //根据角色id查询该角色所关联的菜单信息
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);

        return new ResponseResult(true,200,"根据角色id查询菜单信息成功",menuByRoleId);
    }

    //为角色分配菜单 post请求 参数：roleId  menuId 两个参数封装到RoleMenuVo中
    @RequestMapping("/roleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVO roleMenuVO){

        roleService.roleContextMenu(roleMenuVO);

        return new ResponseResult(true,200,"为角色分配菜单成功",null);
    }

    //删除角色  根据roleId
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer roleId){

        roleService.deleteRole(roleId);

        return new ResponseResult(true,200,"删除角色成功",null);
    }

}
