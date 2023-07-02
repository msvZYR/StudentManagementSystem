package com.zisu.springmvc.mapper;


import com.zisu.springmvc.pojo.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface TeacherMapper {

//    @Select("select * from t_teacher")
    List<Teacher> getAllTeacher();

    @Select("select * from t_teacher where tname = #{tname}")
    Teacher selectTeacherByName(@Param("tname") String tname);

    @Insert("insert into t_teacher values (#{tname},#{tid},#{gender},#{trank})")
    @Options(useGeneratedKeys = true,keyProperty = "tid")
    int insertTeacher(Teacher teacher);

    @Delete("delete from t_teacher where tid = #{tid}")
    int deleteTeacherById(@Param("tid") String tid);

    @Update("update t_teacher set trank=#{trank} where tid = #{tid}")
    int updateTeacherRankById(Teacher teacher);


}
