package com.zisu.springmvc.controller;

import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.pojo.Course;
import com.zisu.springmvc.pojo.Schedule;
import com.zisu.springmvc.pojo.Student;
import com.zisu.springmvc.service.ScheduleService;
import com.zisu.springmvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(value = "/getAllSchedule")
    @ResponseBody
    public List<Schedule> getAllSchedule()
    {
        List<Schedule> scheduleList=scheduleService.getAllSchedule();
        return scheduleList;
    }

    @RequestMapping(value = "/selectScheduleByClazzName")
    @ResponseBody
    public List<Schedule> selectScheduleByClazzName(String clazzname)
    {
        List<Schedule> scheduleList=scheduleService.selectScheduleByClazzName(clazzname);
        return scheduleList;
    }

    @RequestMapping(value = "/selectScheduleByScheduleId")
    @ResponseBody
    public List<Schedule> selectScheduleByScheduleId(String scheduleid)
    {
        List<Schedule> scheduleList=scheduleService.selectScheduleByScheduleId(scheduleid);
        return scheduleList;
    }

//    @RequestMapping(value = "/schedule",method = RequestMethod.GET)
//    public String  getAllSchedule(Model model)
//    {
//        //查询所有学生信息
//        List<Schedule> schedulelist = scheduleService.getAllSchedule();
//        System.out.println("getAllSchedule");
//        model.addAttribute("schedulelist",schedulelist);
//        return "schedule_list";
//    }

    @RequestMapping(value = "/insertSchedule")
    @ResponseBody
    public int insertSchedule(String admin,String coursename, String clazzname, String tname, String roomname)
    {
        Schedule schedule=new Schedule(0,coursename,clazzname,tname,roomname);
        int result=scheduleService.insertSchedule(admin,schedule);
        if(result>0)
            return 1;
        else return 0;
    }

    @RequestMapping(value = "/deleteScheduleById")
    @ResponseBody
    public int deleteScheduleById(String admin,String scheduleid)
    {
        scheduleService.deleteStudentById(admin,scheduleid);
        return 1;
    }

    @RequestMapping(value = "/updateSchedule")
    @ResponseBody
    public int updateSchedule(String admin,int scheduleid,String coursename, String clazzname, String tname, String roomname)
    {
        Schedule schedule=new Schedule(scheduleid,coursename,clazzname,tname,roomname);
        System.out.println("*************************************"+schedule.toString());
        scheduleService.updateSchedule(admin,schedule);
        return 1;
    }

    @RequestMapping(value="/importSchedule")
    @ResponseBody
    public int handleExcelupload(MultipartFile xlsfile, HttpSession session) throws IOException {
        String filename=xlsfile.getOriginalFilename();
        ServletContext servletContext=session.getServletContext();
        String uploadPath=servletContext.getRealPath("")+"upload";
        System.out.println("uploadPath-->"+uploadPath);
        File file=new File(uploadPath);
        if(!file.exists())
            file.mkdir();
        String finalPath=uploadPath+file.separator+filename;
        System.out.println("file.separator-->"+file.separator);
        System.out.println("filename-->"+filename);
        System.out.println("finalPath-->"+finalPath);
        xlsfile.transferTo(new File(finalPath));
        scheduleService.InsertbatchSchedules(finalPath);
        return 1;
    }
    @RequestMapping("/exportSchedule")
    public ResponseEntity<byte[]> handleExceldownload(HttpServletRequest request)throws IOException{
        String filepath=request.getServletContext().getRealPath("/")+"download";
        File file=new File(filepath);
        if(!file.exists())
            file.mkdir();
        scheduleService.saveasexcel(filepath,"scheduleinfo.xls");
        String finalpath=filepath+File.separator+"scheduleinfo.xls";
        System.out.println("finalpath===="+finalpath);
        InputStream is=new FileInputStream(finalpath);
        byte[] bytes=new byte[is.available()];
        is.read(bytes);
        MultiValueMap<String,String> headers=new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename=scheduleinfo.xls");
        HttpStatus statusCode=HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(bytes,headers,statusCode);
        is.close();
        return responseEntity;
    }
    @RequestMapping(value = "/getAllSchedule/{pageNum}")
    @ResponseBody
    public PageInfo<Schedule> getSchedulePage(@PathVariable("pageNum") Integer pageNum){
        //获取学生的分页信息
        PageInfo<Schedule> page = scheduleService.getSchedulePage(pageNum);
        return page;
        //将分页数据共享到请求域中
//        model.addAttribute("page", page);
        //跳转到student_list_pages.html
//        return "student_list_pages";
    }

}
