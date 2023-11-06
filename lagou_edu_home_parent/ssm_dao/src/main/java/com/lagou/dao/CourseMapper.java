package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    //多条件课程列表查询  参数：前台传递的参数
    public List<Course> findCourseByCondition(CourseVO courseVO);

    //新增课程信息 + 新增讲师信息
    public void saveCourse(Course course);

    public void saveTeacher(Teacher teacher);

    //回显课程信息（根据id查询对应的课程信息及关联的讲师信息）  返回值:CourseVO既能封装课程信息也能讲师信息
    public CourseVO findCourseById(Integer id);

    //更新课程信息
    public void updateCourse(Course course);

    //更新讲师信息
    public void updateTeacher(Teacher teacher);

    //修改课程状态  参数：service封装好的course 本质就是根据id修改course表中status的值及更新时间
    public void updateCourseStatus(Course course);


}
