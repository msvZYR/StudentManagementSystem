package com.zisu.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.pojo.Schedule;
import com.zisu.springmvc.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;

public interface ScheduleService {
    public List<Schedule> getAllSchedule();

    public int insertSchedule(String admin,Schedule schedule);
    public List<Schedule> selectScheduleByClazzName(String clazzname);
    public List<Schedule> selectScheduleByScheduleId(String scheduleid);
    public int deleteStudentById(String admin,String scheduleid);
    public int updateSchedule(String admin,Schedule schedule);

    public void InsertbatchSchedules(String finalPath);
    //(9)导出Excel文件
    public void saveasexcel(String finalPath,String filename)throws IOException;
    PageInfo<Schedule> getSchedulePage(Integer pageNum);

}
