package com.zisu.springmvc.mapper;

import com.zisu.springmvc.pojo.Clazz;
import com.zisu.springmvc.pojo.Course;
import com.zisu.springmvc.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CourseMapper {
//    @Select("select * from t_course")
    List<Course> getAllCourse();

    @Select("select * from t_course where coursename = #{coursename}")
    List<Course> selectCourseByName(@Param("coursename") String coursename);

    @Select("select * from t_course where courseid = #{courseid}")
    List<Course> selectCourseById(@Param("courseid") String courseid);

    @Select("select coursename from t_course where courseid = #{courseid}")
    String selectCourseNameById(String courseid);

    @Insert("insert into t_course values (#{courseid},#{coursename},#{college},#{week},#{credit},#{period})")
    int insertCourse(Course course);

    @Delete("delete from t_course where courseid = #{courseid}")
    int deleteCourseById(@Param("courseid") String courseid);

    @Update("update t_course set college=#{college} where courseid = #{courseid}")
    int updateCourseTidById(Course course);

    @Update("update t_course set coursename=#{coursename},college=#{college},week=#{week},credit=#{credit},period=#{period} where courseid = #{courseid}")
    int updateCourse(Course course);

    @Select("select * from t_course where college = #{college} and courseid=#{courseid}")
    Course getCourseByTIdAndCourseId(Course course);

    void InsertbatchCourses(@Param("courses")List<Course> courses);


}
