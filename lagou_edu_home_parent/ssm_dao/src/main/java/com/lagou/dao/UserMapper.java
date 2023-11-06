package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    //用户分页&多条件组合查询  前端传递参数多个 并且个数不确定 返回值用UserVO接收
    public List<User> findAllUserByPage(UserVO userVO);

    //更新用户状态
    public void updateUserStatus(User user);

    //用户登录：根据用户名（手机号）查询具体的用户信息
    public User login(User user);

    //添加用户（注册用户） 将密码加密
    public void saveUser(User user);


    //根据用户id清空用户表与角色表的中间表的关联关系
    public void deleteUserContextRole(Integer userId);

    //分配角色：向中间表添加记录  前端传递参数：roleIdList  userId 用userVo接收  service层封装好user_role_relation 传递到dao
    public void userContextRole(User_Role_relation user_role_relation);

    //动态菜单显示：需要编写下面4个功能(sql是重点)

    //1.根据用户id查询该用户关联的角色信息 （分配角色回显）
    public List<Role> findUserRelationRoleById(Integer id);

    //2.根据角色id，查询角色所拥有的顶级菜单信息（parent_id=-1） 多个角色id
    public List<Menu> findParentMenuByRoleId(List<Integer> roleIds);

    //3.根据PID(parent_id),查询子级菜单信息
    public List<Menu> findSubMenuByPid(Integer pid);

    //4.获取用户拥有的资源权限信息：根据角色id来获取资源信息
    public List<Resource> findResourceByRoleId(List<Integer> roleIds);

    public List<Resource> findResourceByRoleId2(List<Integer> roleIds);

    
    public void test11();
    public void test21();
    public void test31();
    public void test41();
    public void test51();
    public void test61();
    public void test7881();

    public void test71();

}





















