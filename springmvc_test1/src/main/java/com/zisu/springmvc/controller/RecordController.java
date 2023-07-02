package com.zisu.springmvc.controller;

import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.pojo.Clazz;
import com.zisu.springmvc.pojo.Record;
import com.zisu.springmvc.pojo.Schedule;
import com.zisu.springmvc.pojo.Student;
import com.zisu.springmvc.service.ClazzService;
import com.zisu.springmvc.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RecordController {
    @Autowired
    RecordService recordService;

    @RequestMapping(value = "/getAllRecord")
    @ResponseBody
    public List<Record> getAllRecord()
    {
        List<Record> recordList=recordService.getAllRecord();
        return recordList;
    }

    @RequestMapping(value = "/selectRecordByUid")
    @ResponseBody
    public List<Record> selectRecordByUid(String uid)
    {
        List<Record> recordList=recordService.selectRecordByUid(uid);
        return recordList;
    }
    @RequestMapping(value = "/getAllRecord/{pageNum}")
    @ResponseBody
    public PageInfo<Record> getRecordPage(@PathVariable("pageNum") Integer pageNum){
        //获取学生的分页信息
        PageInfo<Record> page = recordService.getRecordPage(pageNum);
        return page;
        //将分页数据共享到请求域中
//        model.addAttribute("page", page);
        //跳转到student_list_pages.html
//        return "student_list_pages";
    }


}
