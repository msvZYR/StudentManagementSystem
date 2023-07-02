package com.zisu.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.pojo.Course;
import com.zisu.springmvc.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;

public interface CourseService {
    public List<Course> getAllCourse();
    public List<Course> selectCourseByName(@Param("coursename") String coursename);
    public List<Course> selectCourseById(@Param("courseid") String courseid);
    public int insertCourse(String admin,Course course);
    public int deleteCourseById(String admin,String courseid);

    int updateCourseTidById(Course course);
    int updateCourse(String admin,Course course);

    public Course getCourseByTIdAndCourseId(Course course);

    public void InsertbatchCourses(String finalPath);
    //(9)导出Excel文件
    public void saveasexcel(String finalPath,String filename)throws IOException;
    PageInfo<Course> getCoursePage(Integer pageNum);
}
