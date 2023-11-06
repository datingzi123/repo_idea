package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /*
        课程内容展示：
            根据课程id，查询关联的章节信息及章节信息关联的课时信息  返回值：章节（包含课时）
            一个课程有多个章节，一个章节有多个课时
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    //回显章节对应的课程信息(id courseName)
    public Course findCourseByCourseId(Integer courseId);

    //新建保存章节信息
    public void saveSection(CourseSection section);

    //修改章节信息
    public void updateSection(CourseSection section);

    //修改章节状态
    public void updateSectionStatus(Integer id,Integer status);

    //新建课时信息
    public void saveLesson(CourseLesson lesson);
}
