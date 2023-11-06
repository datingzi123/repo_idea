package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;

import java.util.List;

public interface UserService {

    //用户分页&多条件组合查询
    public PageInfo findAllUserByPage(UserVO userVO);

    //更新用户状态
    public void updateUserStatus(Integer id,String status);

    //用户登录：根据用户名（手机号）查询用户
    public User login(User user) throws Exception;

    public void saveUser(User user) throws Exception;

    //根据用户id查询该用户关联的角色信息  分配角色（回显）
    public List<Role> findUserRelationRoleById(Integer id);

    //分配角色(用户关联角色)：先清空中间表的关联关系，再向中间表中添加记录
    public void userContextRole(UserVO userVO);

    //获取用户权限，进行菜单动态展示
    public ResponseResult getUserPermissions(Integer userId);
}
