package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //用户分页&多条件组合查询
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){

        PageInfo pageInfo = userService.findAllUserByPage(userVO);

        return new ResponseResult(true,200,"用户分页&多条件组合查询成功",pageInfo);
    }

    //更新用户状态 ENABLE能登录 DISABLE不能登录
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status){

        userService.updateUserStatus(id,status);

        Map<String, String> map = new HashMap<>();
        map.put("status",status);

        return new ResponseResult(true,200,"更新用户状态成功",map);
    }

    //用户登录：根据用户名（手机号）查询具体的用户信息  get请求：携带参数用户名以及密码
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        //接收前端传递参数user,调用service,查询数据库，得到user1
        User user2 = userService.login(user);

        if(user2 != null){

            //保存从数据库查询的用户id及access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();//保证不重复
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user2.getId());

            //将查询处理的信息响应给前台
            Map<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user2.getId());

            return new ResponseResult(true,200,"登录成功",map);

        }else {

            return new ResponseResult(true,200,"用户名或密码错误",null);
        }
    }

    //添加用户  post请求
    @RequestMapping("/saveUser")
    public ResponseResult saveUser(@RequestBody User user) throws Exception {


        userService.saveUser(user);

        return new ResponseResult(true,200,"添加用户成功",null);

    }

    //根据用户id查询该用户关联的角色信息  分配角色（回显）  get请求：传递参数id
    @RequestMapping("/findUserRelationRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){

        List<Role> roleList = userService.findUserRelationRoleById(id);

        return new ResponseResult(true,200,"根据用户id查询角色信息分配角色回显成功",roleList);
    }

    //分配角色（用户关联角色） post请求  UserVO接收前端参数:roleIdList  userId
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){

        userService.userContextRole(userVO);

        return new ResponseResult(true,200,"分配角色成功",null);
    }

    //获取用户权限，进行菜单动态展示  get请求不携带参数
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        //1.获取请求头中的token访问令牌
        String header_token = request.getHeader("Authorization");

        //2.获取session中的token
        String session_token = (String) request.getSession().getAttribute("access_token");

        //3.判断token 是否一致
        if(header_token.equals(session_token)){
            //获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            //调用service，进行菜单信息查询
            ResponseResult responseResult = userService.getUserPermissions(user_id);

            return responseResult;
        }else {

            return new ResponseResult(false,400,"获取菜单信息失败",null);
        }
    }
}
