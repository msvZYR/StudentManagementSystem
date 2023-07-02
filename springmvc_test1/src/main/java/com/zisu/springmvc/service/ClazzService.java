package com.zisu.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.pojo.Clazz;
import com.zisu.springmvc.pojo.Record;
import com.zisu.springmvc.pojo.Schedule;
import com.zisu.springmvc.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ClazzService {
    public List<Clazz> getAllClass();
    public List<Clazz> selectClassByName(@Param("clazzname") String clazzname);
    public List<Clazz> selectClassById(@Param("clazzid") String clazzid);
    public String selectClassNameById(String clazzid);
    public int insertClass(String admin,Clazz clazz);
    public int deleteClassById(String admin,String clazzid);


    public int updateClassNameById(Clazz clazz);
    public int updateClass(String admin,Clazz clazz);

    public List<Student> selectStudentsByClassId(@Param("clazzid") String clazzid);

    public List<Schedule> selectScheduleByClassId(@Param("clazzid") String clazzid);

    public List<Clazz> getClassByTIdAndCourseId(Schedule schedule);

    public void InsertbatchClazzs(String finalPath);
    //(9)导出Excel文件
    public void saveasexcel(String finalPath,String filename)throws IOException;
    PageInfo<Clazz> getClazzPage(Integer pageNum);
}
