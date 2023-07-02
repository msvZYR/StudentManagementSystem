package com.zisu.springmvc.service;

import com.zisu.springmvc.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherService {


    public List<Teacher> getAllTeacher();
    public Teacher selectTeacherByName(@Param("tname") String tname);
    public int insertTeacher(Teacher teacher);
    public int deleteTeacherById(@Param("tid") String tid);

    public int updateTeacherRankById(Teacher teacher);
//    public void deleteTeacherById(String tid);
//    public void updateTeacherRankById(@Param("tid") String tid,
//                               @Param("trank")String trank);
//    public List<Course> getCourseByTId(String tid);
//    public Course getCourseByTIdAndCourseName(@Param("tid") String tid,
//                                       @Param("courseid") String courseid);
//    public List<Clazz> getClassByTIdAndCourseName(@Param("tid") String tid,
//                                           @Param("courseid") String courseid);
//    public List<Student> getStudentByTIdAndCourseName(@Param("tid") String tid,
//                                               @Param("courseid") String courseid);
}
