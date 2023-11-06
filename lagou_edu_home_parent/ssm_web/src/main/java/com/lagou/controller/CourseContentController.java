package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam Integer courseId){

        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", sectionList);
        return responseResult;
    }


    //回显章节对应的课程信息(id courseName)
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){

        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", course);
        return responseResult;
    }

    //新建保存&修改章节信息
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section){

        //判断是否携带id
        if(section.getId() == null){

            courseContentService.saveSection(section);
            ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
            return responseResult;
        }else {
            courseContentService.updateSection(section);
            ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
            return responseResult;
        }
    }

    //修改章节状态 0隐藏 1待更新 2已发布
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id,Integer status){

        courseContentService.updateSectionStatus(id,status);

        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);

        return new ResponseResult(true,200,"响应成功",map);
    }

    //新建课时信息
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson lesson){

        courseContentService.saveLesson(lesson);

        return new ResponseResult(true,200,"响应成功",null);
    }
}
