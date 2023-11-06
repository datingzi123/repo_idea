package com.lagou.service.impl;

import com.lagou.dao.TmenuMapper;
import com.lagou.domain.Tmenu;
import com.lagou.service.TmenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TmenuServiceImpl implements TmenuService {

    @Autowired
    private TmenuMapper tmenuMapper;

    //查询父子菜单进行菜单列表展示，权限粒度到菜单末级
    @Override
    public List<Tmenu> findTmenuByPid(Integer pid) {

        List<Tmenu> tmenuListfu = tmenuMapper.findTmenuByPid(pid);//父菜单  0

       /* for (Tmenu tmenu : tmenuListfu) {

            digui(tmenu);
        }*/
        return tmenuListfu;
    }


    // 给这个方法一个 菜单对象， 查询这个菜单对象的子菜单，并且 复制给 裁断对象的 childs字段
    public void digui(Tmenu tmenu){

        List<Tmenu> tmenuListzi = tmenuMapper.findTmenuByPid(tmenu.getId());//子菜单

        if(tmenuListzi != null && tmenuListzi.size() > 0){

            for (Tmenu tmenu1 : tmenuListzi) {
                digui(tmenu1);
            }
            tmenu.setChilds(tmenuListzi);
        }
    }
}
