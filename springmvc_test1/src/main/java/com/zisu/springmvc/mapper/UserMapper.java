package com.zisu.springmvc.mapper;

import com.zisu.springmvc.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserMapper {
    User findByUidAndPwd(@Param("uid") String uid,@Param("pwd") String pwd);
//
//    List<User> findByType(int type);
//
//    @Transactional
//    @Modifying
//    int deleteByUid(String uid);

//    @Select("select * from t_user where userid = #{uid}")
    User existsByUid(@Param("uid") String uid);
    List<User> getOneUser(String userid);
    List<User> getAllUser();


    @Update("update t_user set password=#{password} where userid = #{userid}")
    int resetPwd(@Param("userid") String userid,@Param("password") String password);

    @Update("update t_user set password=#{password} where userid = #{userid}")
    int resetPwdByManager(@Param("userid") String userid,@Param("password") String password);
    @Select("select username from t_user where userid = #{userid}")
    String selectNameById(String userid);
//    boolean existsByUidAndType(String uid, int type);
//
//    User findByUid(String uid);
//
//    List<User> findByTypeAndUidLikeOrTypeAndUnameLike(int type, String uid, int type2, String uname);
//
//    @Transactional
//    @Modifying
//    @Query(value = "update User u set u.uname=:uname,u.pwd=:pwd,u.college=:college,u.profession=:profession,u.stu_class=:stuClass"
//            + " where u.uid=:uid", nativeQuery = true)
//    int updateUser(@Param("uid") String uid, @Param("uname") String uname, @Param("pwd") String pwd, @Param("college") String college, @Param("profession") String profession, @Param("stuClass") String stuClass);
//
//    @Transactional
//    @Modifying
//    @Query(value = "update User u set u.pwd=:pwd where u.uid=:uid", nativeQuery = true)
//    int resetPwd(@Param("uid") String uid, @Param("pwd") String pwd);
}
