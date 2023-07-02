package com.zisu.springmvc.controller;

import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.pojo.Course;
import com.zisu.springmvc.pojo.Schedule;
import com.zisu.springmvc.pojo.Student;
import com.zisu.springmvc.pojo.Teacher;
import com.zisu.springmvc.service.StudentService;
import com.zisu.springmvc.service.TeacherService;
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
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/getAllStudent",method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getAllStudent()
    {
        List<Student> studentList=studentService.getAllStudent();
        System.out.println("studentList:====="+studentList);
        return studentList;
    }

//    @RequestMapping(value = "/getAllStudent",method = RequestMethod.GET)
//    @ResponseBody
//    public int getAllStudent()
//    {
//        List<Student> studentList=studentService.getAllStudent();
//        return 1;
//    }
    //(1)查询所有学生信息-->/student-->get
    //public List<Student> getAllStudents();
//    @RequestMapping(value = "/student",method = RequestMethod.GET)
//    public String  getAllStudent(Model model)
//    {
//        //查询所有学生信息
//        List<Student> studentlist = studentService.getAllStudent();
//        System.out.println("getAllStudent");
//        model.addAttribute("studentlist",studentlist);
//        return "student_list";
//    }
    @RequestMapping(value = "/getAllStudent/{pageNum}")
    @ResponseBody
    public PageInfo<Student> getStudentPage(@PathVariable("pageNum") Integer pageNum){
        //获取学生的分页信息
        PageInfo<Student> page = studentService.getStudentPage(pageNum);
        return page;
        //将分页数据共享到请求域中
//        model.addAttribute("page", page);
        //跳转到student_list_pages.html
//        return "student_list_pages";
    }

    @ResponseBody
    @RequestMapping(value = "/selectStudentByName",method = RequestMethod.GET)
    public List<Student> selectStudentByName(String stuname) {
        List<Student> studentList=studentService.selectStudentByName(stuname);
        System.out.println("selectStudentByName==="+studentList);
        return studentList;
    }

    @ResponseBody
    @RequestMapping(value = "/selectStudentByClazz",method = RequestMethod.GET)
    public List<Student> selectStudentByClazz(String clazzname) {
        List<Student> studentList=studentService.selectStudentByClazz(clazzname);
        return studentList;
    }

    @ResponseBody
    @RequestMapping(value = "/selectStudentById",method = RequestMethod.GET)
    public List<Student> selectStudentById( String stuid) {
        List<Student> studentList=studentService.selectStudentById(stuid);
        System.out.println("selectStudentById==="+studentList);
        return studentList;
    }

    @RequestMapping(value = "/insertStudent")
    @ResponseBody
    public int insertStudent(String admin,String stuid,String stuname,String college,String profession,String clazzname)
    {
        List<Student> studentList =studentService.selectStudentById(stuid);
//        System.out.println("insertStudent==studentList====="+stuid+" "+stuname+" "+clazzname);

        if(studentList.isEmpty()){
            Student student=new Student(stuid,stuname,college,profession,clazzname);
            System.out.println("insertStudent==studentList====="+stuid+" "+stuname+" "+college+" "+profession+" "+clazzname);
            studentService.insertStudent(admin,student);
            return 1;
        }
        else{
            return 0;
        }

//        if(result>0)
//            return student;
//        else return null;

    }

    @RequestMapping(value = "/deleteStudentById")
    @ResponseBody
    public int deleteStudentById(String admin,String stuid)
    {
        studentService.deleteStudentById(admin,stuid);
        System.out.println("deleteStudentById===="+stuid);
        return 1;
    }

    @RequestMapping(value = "/student",method = RequestMethod.PUT)
    @ResponseBody
    public List<Student> updateStudentNameById(@RequestBody Student student)
    {
        studentService.updateStudentNameById(student);
        return studentService.getAllStudent();

    }

    @RequestMapping(value = "/updateStudent")
    @ResponseBody
    public int updateStudent(String admin,String stuid,String stuname,String college,String profession,String clazzname)
    {
        Student student=new Student(stuid,stuname,college,profession,clazzname);
        studentService.updateStudent(admin,student);
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/studentcourse/{stuid}",method = RequestMethod.GET)
    public List<Course> getCourseByStuId(@PathVariable("stuid") String stuid) {
        List<Course> courseList=studentService.getCourseByStuId(stuid);
        return courseList;
    }
    @ResponseBody
    @RequestMapping(value = "/studentbytc",method = RequestMethod.PUT)
    public List<Student> getStudentByTIdAndCourseId(@RequestBody Schedule schedule){
        List<Student> studentList=studentService.getStudentByTIdAndCourseId(schedule);
        return studentList;
    }

    @RequestMapping(value="/importStu")
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
        studentService.InsertbatchStudents(finalPath);
        return 1;
    }
    @RequestMapping("/exportStu")
    public ResponseEntity<byte[]> handleExceldownload(HttpServletRequest request)throws IOException{
        String filepath=request.getServletContext().getRealPath("/")+"download";
        File file=new File(filepath);
        if(!file.exists())
            file.mkdir();
        studentService.saveasexcel(filepath,"studentinfo.xls");
        String finalpath=filepath+File.separator+"studentinfo.xls";
        System.out.println("finalpath===="+finalpath);
        InputStream is=new FileInputStream(finalpath);
        byte[] bytes=new byte[is.available()];
        is.read(bytes);
        MultiValueMap<String,String> headers=new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename=studentinfo.xls");
        HttpStatus statusCode=HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(bytes,headers,statusCode);
        is.close();
        return responseEntity;
    }
}
