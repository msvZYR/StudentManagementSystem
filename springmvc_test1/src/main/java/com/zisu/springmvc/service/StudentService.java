package com.zisu.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.pojo.Course;
import com.zisu.springmvc.pojo.Schedule;
import com.zisu.springmvc.pojo.Student;
import com.zisu.springmvc.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    public List<Student> getAllStudent();
    public List<Student> selectStudentByName(@Param("stuname") String stuname);
    public List<Student> selectStudentById(@Param("stuid") String stuid);
    public List<Student> selectStudentByClazz(@Param("clazzname") String clazzname);
    public int insertStudent(String admin,Student student);
    public int deleteStudentById(String admin,String stuid);

    public int updateStudentNameById(Student student);
    public int updateStudent(String admin,Student student);
    public List<Course> getCourseByStuId(@Param("stuid") String stuid);

    public List<Student> getStudentByTIdAndCourseId(Schedule schedule);

    public void InsertbatchStudents(String finalPath);
    //(9)导出Excel文件
    public void saveasexcel(String finalPath,String filename)throws IOException;
    //（2）获取学生的分页信息
    PageInfo<Student> getStudentPage(Integer pageNum);



}
