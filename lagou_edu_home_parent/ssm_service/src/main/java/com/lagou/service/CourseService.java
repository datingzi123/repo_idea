package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    //多条件课程列表查询  参数：前台传递的参数courseVO
    public List<Course> findCourseByCondition(CourseVO courseVO);

    //添加课程及讲师信息
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    //回显课程信息（根据id查询对应的课程信息及关联的讲师信息）
    public CourseVO findCourseById(Integer id);

    //更新课程信息：课程信息+讲师信息
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    //课程状态变更
    public void updateCourseStatus(int courseid,int status);


}
