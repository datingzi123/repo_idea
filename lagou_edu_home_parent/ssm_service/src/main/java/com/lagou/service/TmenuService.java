package com.lagou.service;

import com.lagou.domain.Tmenu;

import java.util.List;

public interface TmenuService {

    //查询父子菜单进行菜单列表展示，权限粒度到菜单末级
    public List<Tmenu> findTmenuByPid(Integer pid);
}
