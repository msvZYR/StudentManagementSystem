package com.zisu.springmvc.service;


import com.zisu.springmvc.pojo.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    int loginType(String userid, String password);
//
//    List<User> getAllStudent();
//
    List<User> getOneUser(String userid);
    List<User> getAllUser();
    int resetPwd(@Param("userid") String userid,@Param("password") String password);
    int resetPwdByManager(@Param("userid") String userid);
//
//    int deleteStudentById(String admin, String userId);
//
//    int addStudent(String admin, String userId, String userName, String college, String profession, String stuClass);
//
//    int updateStudent(String admin, String userId, String userName, String college, String profession, String stuClass);
//
//    List<User> search(String keywords);
//
//    boolean getFlag();
//
//    boolean setFlag();
//

//
//    void export(String path, HttpServletResponse response);
//
//    void imports(String path, HttpServletResponse response);
}
