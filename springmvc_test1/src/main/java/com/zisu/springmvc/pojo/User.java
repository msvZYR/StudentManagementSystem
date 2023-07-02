package com.zisu.springmvc.pojo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

public class User {
//    @NotBlank(message = "登录名不能为空")
    private String userid;
//    @NotEmpty(message = "密码不能为空")
    private String username;
    private String password;
    private int age;
    private String email;
    private String phone;
    private String type;

    public User(String userid, String username, String password, int age, String email, String phone, String type) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.type = type;
    }

    public User() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
//    @NotBlank(message = "email不能为空")
//    @Email(message = "email格式不对")
//    private String email;
//    @NotBlank(message = "age不能为空")
//    @Range(min=1,max=100,message = "年龄必须在1到100之间")
//    private String age;
//
//    @NotBlank(message = "telephone不能为空")
//    @Pattern(regexp = "[1][3,8][3,6,9][0-9]{8}",message = "电话号码格式不对，第1位为1，第二位为3或8，第三位为3、6、9，长度为11位")
//    private String telephone;

//    public User(String username, String password, String email, String age, String telephone) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.age = age;
//        this.telephone = telephone;
//    }
//
//    public User() {
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getAge() {
//        return age;
//    }
//
//    public void setAge(String age) {
//        this.age = age;
//    }
//
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                ", age=" + age +
//                ", telephone='" + telephone + '\'' +
//                '}';
//    }
}