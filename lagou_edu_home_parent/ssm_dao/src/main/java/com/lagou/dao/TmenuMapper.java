package com.lagou.dao;

import com.lagou.domain.Tmenu;

import java.util.List;

/*
  自己练习
 */
public interface TmenuMapper {

    //查询父子菜单进行菜单列表展示，权限粒度到菜单末级
    public List<Tmenu> findTmenuByPid(Integer pid);
}
