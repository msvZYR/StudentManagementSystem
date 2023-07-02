package com.zisu.springmvc.controller;//package com.zisu.springmvc.controller;
//
//import com.zisu.springmvc.pojo.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//import java.util.List;
//
//@Controller
//public class UserLoginController {
//    @RequestMapping("/userlogin")
//    public String handleUserLogin(){
//        return "login";
//    }
//    @RequestMapping("/login")
//    public String handleLogin(@Valid User user, BindingResult bindingResult, Model model){
//        String msg;
//        String usernamemsg=null;
//        String userpwdmsg=null;
////        String useremailmsg=null;
////        String useragemsg=null;
////        String usertelemsg=null;
//        System.out.println("login: username="+user.getUsername()+" password="+user.getPassword()
////                +" email="+user.getEmail()+" age="+user.getAge()+" telephone="+user.getTelephone()
//        );
//        if(bindingResult.hasErrors()){
//            //校验不成功，返回登陆页面，提示错误信息
//            String field;
//            String fielderrmsg;
//            List<FieldError> errorList=bindingResult.getFieldErrors();
//            for(FieldError fieldError:errorList){
//                field=fieldError.getField();
//                fielderrmsg=fieldError.getDefaultMessage();
//                System.out.println("错误的字段名："+field);
//                System.out.println("错误信息："+fielderrmsg);
//                if("username".equals(field)){
//                    usernamemsg=fielderrmsg;
//                }else if("password".equals(field)){
//                    userpwdmsg=fielderrmsg;
//                }
////                else if ("email".equals(field)) {
////                    useremailmsg = fielderrmsg;
////                }else if ("age".equals(field)) {
////                    useragemsg = fielderrmsg;
////                }else if ("telephone".equals(field)) {
////                    usertelemsg = fielderrmsg;
////                }
//            }
//            model.addAttribute("usernamemsg",usernamemsg);
//            model.addAttribute("userpwdmsg",userpwdmsg);
////            model.addAttribute("useremailmsg",useremailmsg);
////            model.addAttribute("useragemsg",useragemsg);
////            model.addAttribute("usertelemsg",usertelemsg);
//            return "login";
//        }
//        else {
//            if(user.getUsername().equals("admin")&&user.getPassword().equals("admin")){
//                msg="登录成功";
//                model.addAttribute("user",user);
//                model.addAttribute("msg",msg);
//                System.out.println("msg:"+msg);
//                return "mStudent";
//            }else {
//                msg="用户名或密码错误";
//                model.addAttribute("msg",msg);
//                System.out.println("msg:"+msg);
//                return "index";
//            }
//        }
//    }
//    @RequestMapping("logout")
//    public String handleLogout(HttpSession session){
//        session.invalidate();
//        return "redirect:/userlogin";
//    }
//    @RequestMapping("main")
//    public String handleMain(HttpSession session){
//        User user=(User)session.getAttribute("user");
//        System.out.println("user:"+user);
//        return "main";
//    }
//}

import com.zisu.springmvc.pojo.Clazz;
import com.zisu.springmvc.pojo.User;
import com.zisu.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public int login(String userid, String password) {
        System.out.println("Controller:  "+userid+"  "+password);
        return userService.loginType(userid, password);
    }
    @RequestMapping(value = "/mStudent",method = RequestMethod.GET)
    public String  getmStudent(Model model)
    {
        return "mStudent";
    }
    @RequestMapping(value = "/mCenter",method = RequestMethod.GET)
    public String getmCenter(Model model)
    {
        return "mCenter";
    }
    @RequestMapping(value = "/mCourse",method = RequestMethod.GET)
    public String getmCourse(Model model)
    {
        return "mCourse";
    }
    @RequestMapping(value = "/mSelect",method = RequestMethod.GET)
    public String getmSelect(Model model)
    {
        return "mSelect";
    }
    @RequestMapping(value = "/mRecord",method = RequestMethod.GET)
    public String getmRecord(Model model)
    {
        return "mRecord";
    }
    @RequestMapping(value = "/mType",method = RequestMethod.GET)
    public String getmType(Model model)
    {
        return "mType";
    }

    @RequestMapping(value = "/nCenter",method = RequestMethod.GET)
    public String getnCenter(Model model)
    {
        return "nCenter";
    }
    @RequestMapping(value = "/nStudent",method = RequestMethod.GET)
    public String  getnStudent(Model model)
    {
        return "nStudent";
    }
    @RequestMapping(value = "/nCourse",method = RequestMethod.GET)
    public String getnCourse(Model model)
    {
        return "nCourse";
    }
    @RequestMapping(value = "/nSelect",method = RequestMethod.GET)
    public String getnSelect(Model model)
    {
        return "nSelect";
    }
    @RequestMapping(value = "/nType",method = RequestMethod.GET)
    public String getnType(Model model)
    {
        return "nType";
    }

    @RequestMapping("/getOneUser")
    @ResponseBody
    public List<User> getOneUser(String userid) {
        return userService.getOneUser(userid);
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping("/resetPwd")
    @ResponseBody
    public int resetPwd(String userid, String password) {
        System.out.println("resetPwd**********************"+userid+" "+password);
        userService.resetPwd(userid, password);
        return 1;
    }
    @RequestMapping("/resetPwdByManager")
    @ResponseBody
    public int resetPwdByManager(String userid) {
        userService.resetPwdByManager(userid);
        return 1;
    }
//    @RequestMapping("/login")
//    public String login(String userid, String password) {
//        System.out.println("Controller:  "+userid+"  "+password);
//        if(userService.loginType(userid, password)==1) {
//            return "mStudent";
//        }
//        else return "login";
//    }

//    @RequestMapping("/getAllStudent")
//    public List<User> getAllStudent() {
//        return userService.getAllStudent();
//    }
//

//
//    @RequestMapping("/deleteStudentById")
//    public int deleteStudentById(String admin, String userid) {
//        return userService.deleteStudentById(admin, userid);
//    }
//
//    @RequestMapping("/addStudent")
//    public int addStudent(String admin, String userId, String userName, String college, String profession, String stuClass) {
//        return userService.addStudent(admin, userId, userName, college, profession, stuClass);
//    }
//
//    @RequestMapping("/updateStudent")
//    public int updateStudent(String admin, String userId, String userName, String college, String profession, String stuClass) {
//        return userService.updateStudent(admin, userId, userName, college, profession, stuClass);
//    }
//
//    @RequestMapping("/searchStudent")
//    public List<User> searchStudent(String keywords) {
//        return userService.search(keywords);
//    }
//
//    @RequestMapping("/getFlag")
//    public boolean getFlag() {
//        return userService.getFlag();
//    }
//
//    @RequestMapping("/setFlag")
//    public boolean setFlag() {
//        return userService.setFlag();
//    }
//

//
//    @RequestMapping("/importUser")
//    public void importUser(String path, HttpServletResponse response) {
//        path = "C:\\Users\\chenwenjie\\Desktop\\user.xls";
//        userService.imports(path, response);
//    }
//
//    @RequestMapping("/exportUser")
//    public void exportUser(String path, HttpServletResponse response) {
////        path = "C:\\Users\\chenwenjie\\Desktop";
//        userService.export(path, response);
    }
