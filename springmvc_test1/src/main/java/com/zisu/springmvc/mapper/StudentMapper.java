package com.zisu.springmvc.mapper;

import com.zisu.springmvc.pojo.Course;
import com.zisu.springmvc.pojo.Schedule;
import com.zisu.springmvc.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {
//    @Select("select * from t_student")
    public List<Student> getAllStudent();

    @Select("select * from t_student where stuname = #{stuname}")
    List<Student> selectStudentByName(@Param("stuname") String stuname);


    @Select("select * from t_student where stuid = #{stuid}")
    List<Student> selectStudentById(@Param("stuid") String stuid);
    @Select("select * from t_student where clazzname = #{clazzname}")
    List<Student> selectStudentByClazz(@Param("clazzname") String clazzname);
    @Select("select stuname from t_student where stuid = #{stuid}")
    String selectStudentNameById(String stuid);

    @Insert("insert into t_student values (#{stuid},#{stuname},#{clazzname},#{college},#{profession})")
    int insertStudent(Student student);

    @Delete("delete from t_student where stuid = #{stuid}")
    int deleteStudentById(@Param("stuid") String stuid);

    @Update("update t_student set stuname=#{stuname},college=#{college},profession=#{profession} where stuid = #{stuid}")
    int updateStudent(Student student);
    @Update("update t_student set stuname=#{stuname} where stuid = #{stuid}")
    int updateStudentNameById(Student student);

    @Select("select cor.* from t_student stu join t_class cla on stu.clazzname=cla.clazzname\n" +
            "            join t_schedule sche on stu.clazzname=sche.clazzname\n" +
            "            join t_course cor on cor.courseid=sche.courseid\n" +
            "        where stu.stuid = #{stuid}")
    List<Course> getCourseByStuId(@Param("stuid") String stuid);

    @Select("select stu.* from t_class cla join t_schedule sche on cla.clazzname=sche.clazzname\n" +
            "                     join t_student stu on stu.clazzname=cla.clazzname\n" +
            "        where sche.tid = #{tid} and sche.courseid=#{courseid}")
    List<Student> getStudentByTIdAndCourseId(Schedule schedule);

    void InsertbatchStudents(@Param("students")List<Student> students);

}
