package com.lagou.domain;

import java.util.List;

/*
    该类用于接收前端传递参数
 */
public class RoleMenuVO {

    //角色id
    private Integer roleId;
    //所选的菜单列表id
    private List<Integer> menuIdList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
