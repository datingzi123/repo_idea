package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired  //注入代理对象
    private CourseMapper courseMapper;

    //多条件课程列表查询  参数：前台传递的参数courseVO
    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {

        List<Course> courseList = courseMapper.findCourseByCondition(courseVO);

        return courseList;
    }

    //添加课程及讲师信息
    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        //封装课程信息
        Course course = new Course();

        BeanUtils.copyProperties(course,courseVO);//前端传递的参数courseVO封装到course中

        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        //调用dao层方法保存课程
        courseMapper.saveCourse(course);

        //获取新插入数据的id值
        int id = course.getId();

        //封装讲师信息
        Teacher teacher = new Teacher();

        BeanUtils.copyProperties(teacher,courseVO);

        //补全讲师信息
        teacher.setCourseId(id);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);

        //调用dao层方法保存讲师信息
        courseMapper.saveTeacher(teacher);
    }

    //回显课程信息（根据id查询对应的课程信息及关联的讲师信息）
    @Override
    public CourseVO findCourseById(Integer id) {
        CourseVO courseVO = courseMapper.findCourseById(id);
        return courseVO;
    }

    //更新课程信息：课程信息+讲师信息
    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        //把前端传递参数，封装到课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVO);//courseVO中关于课程信息封装到course中

        //补全课程信息
        Date date = new Date();
        course.setUpdateTime(date);

        //调用dao层方法，更新课程信息
        courseMapper.updateCourse(course);

        //把前端传递参数，封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);

        //补全讲师信息   【teacher表的courseId】
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);

        //调用dao层方法，更新讲师信息
        courseMapper.updateTeacher(teacher);
    }

    //修改课程状态
    @Override
    public void updateCourseStatus(int courseid, int status) {

        //封装数据  参数是controller（前端）传递的值封装到course表中，传递给dao
        Course course = new Course();
        course.setStatus(status);
        course.setId(courseid);
        course.setUpdateTime(new Date());

        //调用dao
        courseMapper.updateCourseStatus(course);
    }




}


















