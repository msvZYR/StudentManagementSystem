package com.zisu.springmvc.controller;

import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.pojo.Clazz;
import com.zisu.springmvc.pojo.Course;
import com.zisu.springmvc.pojo.Student;
import com.zisu.springmvc.service.CourseService;
import com.zisu.springmvc.service.StudentService;
import org.apache.ibatis.annotations.Param;
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
public class CourseController {
    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/getAllCourse")
    @ResponseBody
    public List<Course> getAllCourse()
    {
        List<Course> courseList=courseService.getAllCourse();
        System.out.println("getAllCourse============="+courseList);
        return courseList;
    }
//    @RequestMapping(value = "/course",method = RequestMethod.GET)
//    public String  getAllCourse(Model model)
//    {
//        //查询所有学生信息
//        List<Course> courselist = courseService.getAllCourse();
//        System.out.println("getAllCourse");
//        model.addAttribute("courselist",courselist);
//        return "course_list";
//    }

    @ResponseBody
    @RequestMapping(value = "/selectCourseByName")
    public List<Course> selectCourseByName(String coursename) {
        List<Course> courseList=courseService.selectCourseByName(coursename);
        return courseList;
    }

    @ResponseBody
    @RequestMapping(value = "/selectCourseById")
    public List<Course> selectCourseById(String courseid) {
        List<Course> courseList=courseService.selectCourseById(courseid);
        System.out.println("selectCourseById============="+courseList);
        return courseList;
    }


    @RequestMapping(value = "/insertCourse")
    @ResponseBody
    public int insertCourse(String admin,String courseid,String coursename,String college,String week,int credit,int period)
    {
        List<Course> courseList=courseService.selectCourseById(courseid);
        if(courseList.isEmpty()){
            Course course=new Course(courseid,coursename,college,week,credit,period);
            System.out.println("insertCourse===========&&&&&&&&&&&&&&&&&&&&&&&========"+course.toString());
            courseService.insertCourse(admin,course);
            return 1;
        }
        else{
            return 0;
        }
    }

    @RequestMapping(value = "/deleteCourseById")
    @ResponseBody
    public int deleteCourseById(String admin,String courseid)
    {
        courseService.deleteCourseById(admin,courseid);
        return 1;
    }

    @RequestMapping(value = "/course",method = RequestMethod.PUT)
    @ResponseBody
    public List<Course> updateCourseTidById(@RequestBody Course course)
    {
        courseService.updateCourseTidById(course);
        return courseService.getAllCourse();

    }

    @RequestMapping(value = "/updateCourse")
    @ResponseBody
    public int updateCourse(String admin,String courseid,String coursename,String college,String week,int credit,int period)
    {
        Course course=new Course(courseid,coursename,college,week,credit,period);
        System.out.println("updateCourse===========&&&&&&&&&&&&&&&&&&&&&&&========"+course.toString());
        courseService.updateCourse(admin,course);
        return 1;

    }

    @RequestMapping(value = "/coursebytc",method = RequestMethod.PUT)
    @ResponseBody
    public Course getCourseByTIdAndCourseId(@RequestBody Course course){
        Course cor=courseService.getCourseByTIdAndCourseId(course);
        return cor;
    }

    @RequestMapping(value="/importCourse")
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
        courseService.InsertbatchCourses(finalPath);
        return 1;
    }
    @RequestMapping("/exportCourse")
    public ResponseEntity<byte[]> handleExceldownload(HttpServletRequest request)throws IOException{
        String filepath=request.getServletContext().getRealPath("/")+"download";
        File file=new File(filepath);
        if(!file.exists())
            file.mkdir();
        courseService.saveasexcel(filepath,"courseinfo.xls");
        String finalpath=filepath+File.separator+"courseinfo.xls";
        System.out.println("finalpath===="+finalpath);
        InputStream is=new FileInputStream(finalpath);
        byte[] bytes=new byte[is.available()];
        is.read(bytes);
        MultiValueMap<String,String> headers=new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename=courseinfo.xls");
        HttpStatus statusCode=HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(bytes,headers,statusCode);
        is.close();
        return responseEntity;
    }

    @RequestMapping(value = "/getAllCourse/{pageNum}")
    @ResponseBody
    public PageInfo<Course> getCoursePage(@PathVariable("pageNum") Integer pageNum){
        PageInfo<Course> page = courseService.getCoursePage(pageNum);
        return page;
    }
}
