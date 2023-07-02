package com.zisu.springmvc.controller;

import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.pojo.*;
import com.zisu.springmvc.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
public class ClazzController {
    @Autowired
    ClazzService clazzService;


    @RequestMapping(value = "/getAllClass")
    @ResponseBody
    public List<Clazz> getAllClass()
    {
        List<Clazz> clazzList=clazzService.getAllClass();
        System.out.println("getAllClass============="+clazzList);
        return clazzList;
    }
//    @RequestMapping(value = "/clazz",method = RequestMethod.GET)
//    public String  getAllClass(Model model)
//    {
//        //查询所有学生信息
//        List<Clazz> clazzlist = clazzService.getAllClass();
//        System.out.println("getAllClass");
//        model.addAttribute("clazzlist",clazzlist);
//        return "clazz_list";
//    }

    @ResponseBody
    @RequestMapping(value = "/selectClassByName")
    public List<Clazz> selectClassByName(String clazzname){
        List<Clazz> clazzList=clazzService.selectClassByName(clazzname);
        return clazzList;
    }

    @ResponseBody
    @RequestMapping(value = "/selectClassById")
    public List<Clazz> selectClassById(String clazzid){
        List<Clazz> clazzList=clazzService.selectClassById(clazzid);
        return clazzList;
    }

    @ResponseBody
    @RequestMapping(value = "/selectClassNameById")
    public String selectClassNameById(String clazzid){
        String clazzname=clazzService.selectClassNameById(clazzid);
        return clazzname;
    }


    @RequestMapping(value = "/insertClass")
    @ResponseBody
    public int insertClass(String admin,String clazzid,String clazzname,String clazztname)
    {
        List<Clazz> clazzList=clazzService.selectClassById(clazzid);
        if(clazzList.isEmpty()){
            Clazz clazz=new Clazz(clazzid,clazzname,clazztname);
            System.out.println("insertCourse===========&&&&&&&&&&&&&&&&&&&&&&&========"+clazz.toString());
            clazzService.insertClass(admin,clazz);
            return 1;
        }
        else{
            return 0;
        }
    }

    @RequestMapping(value = "/deleteClassById")
    @ResponseBody
    public int deleteClassById(String admin,String clazzid)
    {
        clazzService.deleteClassById(admin,clazzid);



        return 1;
    }

    @RequestMapping(value = "/updateClass")
    @ResponseBody
    public int updateClass(String admin,String clazzid,String clazzname,String clazztname)
    {
        Clazz clazz=new Clazz(clazzid,clazzname,clazztname);
        clazzService.updateClass(admin,clazz);
        return 1;

    }

    @RequestMapping(value = "/clazz",method = RequestMethod.PUT)
    @ResponseBody
    public List<Clazz> updateClassNameById(@RequestBody Clazz clazz)
    {
        clazzService.updateClassNameById(clazz);
        return clazzService.getAllClass();

    }

    @ResponseBody
    @RequestMapping(value = "/clazzstudent/{clazzid}",method = RequestMethod.GET)
    public List<Student> selectStudentsByClassId(@PathVariable("clazzid") String clazzid){
        List<Student> studentList=clazzService.selectStudentsByClassId(clazzid);
        return studentList;
    }

    @ResponseBody
    @RequestMapping(value = "/clazzschedule/{clazzid}",method = RequestMethod.GET)
    public List<Schedule> selectScheduleByClassId(@PathVariable("clazzid") String clazzid) {
        List<Schedule> scheduleList=clazzService.selectScheduleByClassId(clazzid);
        return scheduleList;
    }

    @ResponseBody
    @RequestMapping(value = "/clazzbytc",method = RequestMethod.PUT)
    public List<Clazz> getClassByTIdAndCourseId(@RequestBody Schedule schedule) {
        List<Clazz> clazzList=clazzService.getClassByTIdAndCourseId(schedule);
        return clazzList;
    }

    @RequestMapping(value="/importClazz")
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
       clazzService.InsertbatchClazzs(finalPath);
        return 1;
    }
    @RequestMapping("/exportClazz")
    public ResponseEntity<byte[]> handleExceldownload(HttpServletRequest request)throws IOException{
        String filepath=request.getServletContext().getRealPath("/")+"download";
        File file=new File(filepath);
        if(!file.exists())
            file.mkdir();
        clazzService.saveasexcel(filepath,"clazzinfo.xls");
        String finalpath=filepath+File.separator+"clazzinfo.xls";
        System.out.println("finalpath===="+finalpath);
        InputStream is=new FileInputStream(finalpath);
        byte[] bytes=new byte[is.available()];
        is.read(bytes);
        MultiValueMap<String,String> headers=new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename=clazzinfo.xls");
        HttpStatus statusCode=HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(bytes,headers,statusCode);
        is.close();
        return responseEntity;
    }

    @RequestMapping(value = "/getAllClass/{pageNum}")
    @ResponseBody
    public PageInfo<Clazz> getClazzPage(@PathVariable("pageNum") Integer pageNum){
        //获取学生的分页信息
        PageInfo<Clazz> page = clazzService.getClazzPage(pageNum);
        return page;
        //将分页数据共享到请求域中
//        model.addAttribute("page", page);
        //跳转到student_list_pages.html
//        return "student_list_pages";
    }

}
