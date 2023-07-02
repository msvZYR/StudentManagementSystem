package com.zisu.springmvc.mapper;


import com.zisu.springmvc.pojo.Course;
import com.zisu.springmvc.pojo.Schedule;
import com.zisu.springmvc.pojo.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ScheduleMapper {
//    @Select("select * from t_schedule")
    List<Schedule> getAllSchedule();



    @Insert("insert into t_schedule values (null,#{coursename},#{clazzname},#{tname},#{roomname})")
    int insertSchedule(Schedule schedule);

    @Select("select * from t_schedule where clazzname = #{clazzname}")
    List<Schedule> selectScheduleByClazzName(String clazzname);

    @Select("select * from t_schedule where scheduleid = #{scheduleid}")
    List<Schedule> selectScheduleByScheduleId(String scheduleid);
    @Select("select clazzname from t_schedule where scheduleid = #{scheduleid}")
    String selectClazzNameByScheduleId(String scheduleid);

    @Delete("delete from t_schedule where scheduleid = #{scheduleid}")
    int deleteStudentById(String scheduleid);

    @Update("update t_schedule set coursename=#{coursename},clazzname=#{clazzname},tname=#{tname},roomname=#{roomname} where scheduleid = #{scheduleid}")
    int updateSchedule(Schedule schedule);

    void InsertbatchSchedules(@Param("schedules")List<Schedule> schedules);

}
