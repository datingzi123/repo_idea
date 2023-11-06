package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //用户分页&多条件组合查询
    @Override
    public PageInfo findAllUserByPage(UserVO userVO) {

        //分页查询
        PageHelper.startPage(userVO.getCurrentPage(),userVO.getPageSize());

        List<User> allUserByPage = userMapper.findAllUserByPage(userVO);

        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);

        return pageInfo;
    }

    //更新用户状态
    @Override
    public void updateUserStatus(Integer id, String status) {

        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());

        userMapper.updateUserStatus(user);
    }

    //用户登录：根据用户名（手机号）查询用户
    @Override
    public User login(User user) throws Exception {

        //调用dao方法，查询数据库返回user对象   user1：包含了密文密码  前端传递参数：用户名和密码
        User user1 = userMapper.login(user);

        //调用加密算法MD5中的【验证】方法，将前端参数中的明文密码与查询数据库中的密文密码进行对比
        if(user1 != null && Md5.verify(user.getPassword(),"lagou",user1.getPassword())){
            return user1;
        }
        return null;
    }

    @Override
    public void saveUser(User user) throws Exception {

        Date date = new Date();
        user.setCreate_time(date);
        user.setUpdate_time(date);
        user.setIs_del(0);
        user.setStatus("ENABLE");
        String md5 = Md5.md5(user.getPassword(), "lagou");//将前端传递参数的密码，调用加密算法
        user.setPassword(md5);

        userMapper.saveUser(user);
    }

    //根据用户id查询该用户关联的角色信息  分配角色（回显）
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {

        List<Role> roleList = userMapper.findUserRelationRoleById(id);
        return roleList;
    }

    //分配角色（用户关联角色）
    @Override
    public void userContextRole(UserVO userVO) {

        //根据前端传递参数：用户id来清空中间表的关联关系
        userMapper.deleteUserContextRole(userVO.getUserId());

        //接收前端传递参数，封装到user_role_relation中间表中  参数roleIdList
        for (Integer roleId : userVO.getRoleIdList()) {

            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setRoleId(roleId);
            user_role_relation.setUserId(userVO.getUserId());

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }
    }

    //获取用户权限信息，进行菜单动态展示
    @Override
    public ResponseResult getUserPermissions(Integer userId) {

        //1.获取当前用户拥有的角色 (包含所有的角色信息)
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);

        //2.获取角色id，保存到List集合中
        ArrayList<Integer> roleIds = new ArrayList<>();

        //遍历roleList,取出roleId,放入roleIds集合中
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        //3.根据角色id查询父菜单
        //roleIds.add(1);
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        //4.查询封装父菜单关联的子菜单,遍历父菜单，得到parent_id查询子菜单，把子菜单封装到Menu类中的submenuList属性上
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubmenuList(subMenu);
        }

        //5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //6.封装数据并返回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu); //包含父子菜单信息
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }
}




















