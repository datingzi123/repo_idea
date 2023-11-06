package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /*
        查询所有父子菜单信息： Pid  parentId
            父菜单封装到Menu中的普通属性
            子菜单封装到Menu中的submenuList
     */
    public List<Menu> findSubMenuListByPid(int pid);

    //菜单列表查询
    public List<Menu> findAllMenu();

    //根据id查询菜单
    public Menu findMenuById(Integer id);
}
