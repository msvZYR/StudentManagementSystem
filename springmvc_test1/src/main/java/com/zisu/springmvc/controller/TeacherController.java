package com.zisu.springmvc.controller;

import com.zisu.springmvc.pojo.Teacher;
import com.zisu.springmvc.service.TeacherService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;


    @RequestMapping(value = "/teacher",method = RequestMethod.GET)
    @ResponseBody
    public List<Teacher> getALLTeacher()
    {
        List<Teacher> teacherList=teacherService.getAllTeacher();
        //System.out.println(teacherList);
        return teacherList;
    }

//    @RequestMapping(value = "/teacher",method = RequestMethod.GET)
//    public String getALLTeacher(Model model)
//    {
//        List<Teacher> teacherlist=teacherService.getAllTeacher();
//        System.out.println("getAllTeacher");
//        model.addAttribute("teacherlist",teacherlist);
//        return "teacher_list";
//    }

    @ResponseBody
    @RequestMapping(value = "/teacher/{tname}",method = RequestMethod.GET)
    public Teacher selectTeacherByName(@PathVariable("tname") String tname) {
      Teacher teacher=teacherService.selectTeacherByName(tname);
      System.out.println("tname="+tname+" "+teacher);
      return teacher;
    }
    @RequestMapping(value = "/teacher",method = RequestMethod.POST)
    @ResponseBody
    public Teacher insertTeacher(@RequestBody Teacher teacher)
    {
        int result=teacherService.insertTeacher(teacher);
        if(result>0)
            return teacher;
        else return null;
    }

    @RequestMapping(value = "/teacher/{tid}",method = RequestMethod.DELETE)
    @ResponseBody
    public List<Teacher> deleteTeacherById(@PathVariable("tid") String tid)
    {
        teacherService.deleteTeacherById(tid);
        return teacherService.getAllTeacher();
    }

    @RequestMapping(value = "/teacher",method = RequestMethod.PUT)
    @ResponseBody
    public List<Teacher> updateTeacherRankById(@RequestBody Teacher teacher)
    {
        teacherService.updateTeacherRankById(teacher);
        return teacherService.getAllTeacher();

    }


}
