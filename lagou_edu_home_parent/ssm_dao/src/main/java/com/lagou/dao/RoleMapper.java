package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    //查询所有角色&条件查询  前端传递参数：角色名称
    public List<Role> findAllRole(Role role);

    //根据角色id查询该角色关联的菜单信息id  [1,2,3,5]  sql三表联查
    public List<Integer> findMenuByRoleId(Integer roleId);

    //根据roleId清空中间表与菜单表的关联关系(删除操作没有返回值)
    public void deleteRoleContextMenu(Integer roleId);

    //为角色分配菜单信息（添加操作：向中间表添加记录）
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    //删除角色（根据roleId删除，删除之前先清空角色表与菜单表的关联关系）
    public void deleteRole(Integer roleId);
}
