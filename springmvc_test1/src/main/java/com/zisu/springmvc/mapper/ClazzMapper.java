package com.zisu.springmvc.mapper;
import com.zisu.springmvc.pojo.Clazz;
import com.zisu.springmvc.pojo.Schedule;
import com.zisu.springmvc.pojo.Student;
import com.zisu.springmvc.pojo.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface ClazzMapper {
//    @Select("select * from t_class")
    List<Clazz> getAllClass();
    @Select("select * from t_class where clazzname = #{clazzname}")
    List<Clazz> selectClassByName(@Param("clazzname") String clazzname);

    @Select("select * from t_class where clazzid = #{clazzid}")
    List<Clazz> selectClassById(@Param("clazzid") String clazzid);

    @Select("select clazzname from t_class where clazzid = #{clazzid}")
    String selectClassNameById(String clazzid);
    @Insert("insert into t_class values (#{clazzid},#{clazzname},#{clazztname})")
//    @Options(useGeneratedKeys = true,keyProperty = "clazzid")
    int insertClass(Clazz clazz);
    @Delete("delete from t_class where clazzid = #{clazzid}")
    int deleteClassById(@Param("clazzid") String clazzid);
    @Update("update t_class set clazzname=#{clazzname} where clazzid= #{clazzid}")
    int updateClassNameById(Clazz clazz);

    @Update("update t_class set clazzname=#{clazzname},clazztname=#{clazztname} where clazzid= #{clazzid}")
    int updateClass(Clazz clazz);
    @Select("select stu.* from t_class cla left join t_student stu on cla.clazzname = stu.clazzname where cla.clazzid = #{clazzid}")
    List<Student> selectStudentsByClassId(@Param("clazzid") String clazzid);
    @Select("select sch.* from t_class cla join t_schedule sch on cla.clazzname = sch.clazzname where cla.clazzid = #{clazzid}")
    List<Schedule> selectScheduleByClassId(@Param("clazzid") String clazzid);
    @Select("select cla.* from t_class cla join t_schedule sche on cla.clazzname=sche.clazzname where sche.tid = #{tid} and sche.courseid=#{courseid}")
    List<Clazz> getClassByTIdAndCourseId(Schedule schedule);

    void InsertbatchClazzs(@Param("clazzs")List<Clazz> clazzs);
}
