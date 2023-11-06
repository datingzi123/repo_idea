package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    CourseController层：
        1.接收参数实体类来进行接收，完成封装
        2.调用service方法得到查询结果
        3.把查询结果封装到responseResult进行响应，返回json
 */
@RestController //组合注解
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    //多条件查询课程列表
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){

        List<Course> list = courseService.findCourseByCondition(courseVO);

        //响应结果封装对象
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);

        return responseResult;
    }

    //图片上传
    @RequestMapping("/courseUpload") //映射地址同接口文档 携带参数：图片信息
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }

        //2.获取项目部署路径 request
        // D:\apache-tomcat-8.5.56\webapps\ssm_web\   完整路径
        String realPath = request.getServletContext().getRealPath("/");
        // D:\apache-tomcat-8.5.56\webapps\   截取路径
        String webappsPath = realPath.substring(0, realPath.indexOf("ssm_web"));

        //3.获取原文件名
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名    时间+文件名后缀
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.上传文件
        String uploadPath = webappsPath + "upload\\";
        File filePath = new File(uploadPath, newFileName); //这句不懂

        //如果目录不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录：" + filePath);
        }
        //图片就真正上传了
        file.transferTo(filePath);

        //6.将文件名和文件路径返回 进行响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);

        return responseResult;
    }

    //新增保存&修改课程信息写在同一个方法中
    @RequestMapping("saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        //携带id是修改操作  不携带id是新增操作
        if(courseVO.getId() == null){
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }

    }

    //回显课程信息（根据id查询对应的课程信息及关联的讲师信息）
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam Integer id){
        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseVO);
        return responseResult;
    }

    /*
        修改课程状态:1.接收前端传递的参数，参考接口文档是get请求，携带id和status
                   2.调用service方法，传递参数，完成课程状态的变更
                   3.响应数据
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id,int status){
        courseService.updateCourseStatus(id,status);

        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",map);
        return responseResult;
    }

}














