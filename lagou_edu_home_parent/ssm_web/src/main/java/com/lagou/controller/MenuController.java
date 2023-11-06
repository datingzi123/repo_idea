package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    //菜单列表查询  get请求 全查不携带参数
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> allMenu = menuService.findAllMenu();
        return new ResponseResult(true,200,"菜单列表查询成功",allMenu);
    }

    //回显菜单信息  get请求：携带参数id
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){

        //根据id判断当前是更新还是添加操作  判断id的值是否为-1
        if(id == -1){
            //添加操作  回显信息中不需要查询menu信息,只回显父子级菜单信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"添加操作回显数据成功",map);
        }else {
            //修改操作  需要回显所有menu信息
            Menu menu = menuService.findMenuById(id);

            //回显父子级菜单信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"修改操作回显数据成功",map);
        }
    }
}
