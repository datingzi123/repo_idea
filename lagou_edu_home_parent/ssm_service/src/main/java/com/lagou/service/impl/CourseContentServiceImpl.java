package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {


    @Autowired
    private CourseContentMapper courseContentMapper;

    /*
        课程内容展示：
            根据课程id，查询关联的章节信息及章节信息关联的课时信息  返回值：章节（包含课时）
            一个课程有多个章节，一个章节有多个课时
     */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {

        List<CourseSection> sectionList = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return sectionList;
    }

    //回显章节对应的课程信息(id courseName)
    @Override
    public Course findCourseByCourseId(Integer courseId) {

        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    //新建保存章节信息
    @Override
    public void saveSection(CourseSection section) {

        //接收参数 补全信息 调用dao
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);

        courseContentMapper.saveSection(section);
    }

    //修改章节信息
    @Override
    public void updateSection(CourseSection section) {

        //补全信息
        Date date = new Date();
        section.setUpdateTime(date);

        courseContentMapper.updateSection(section);
    }

    //修改章节状态
    @Override
    public void updateSectionStatus(Integer id, Integer status) {

        //封装数据
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setStatus(status);
        section.setUpdateTime(new Date());

        courseContentMapper.updateSectionStatus(section);
    }

    //新建课时信息
    @Override
    public void saveLesson(CourseLesson lesson) {

        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);

        courseContentMapper.saveLesson(lesson);

    }
}
