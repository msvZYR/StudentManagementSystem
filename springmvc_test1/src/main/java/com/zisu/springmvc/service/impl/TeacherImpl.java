package com.zisu.springmvc.service.impl;

import com.zisu.springmvc.mapper.RecordMapper;
import com.zisu.springmvc.pojo.Teacher;
import com.zisu.springmvc.mapper.TeacherMapper;
import com.zisu.springmvc.service.TeacherService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
//@Transactional
public class TeacherImpl implements TeacherService {
    @Autowired
    public TeacherMapper teacherMapper;
    @Autowired
    RecordMapper recordMapper;

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherMapper.getAllTeacher();
    }

    @Override
    public Teacher selectTeacherByName(String tname) {
        return teacherMapper.selectTeacherByName(tname);
    }

    @Override
    public int insertTeacher(Teacher teacher) {
        return teacherMapper.insertTeacher(teacher);
    }
    @Override
    public int deleteTeacherById(@Param("tid") String tid) {
        return teacherMapper.deleteTeacherById(tid);
    }

    @Override
    public int updateTeacherRankById(Teacher teacher) {
        return teacherMapper.updateTeacherRankById(teacher);
    }

}
