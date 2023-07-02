package com.zisu.springmvc.pojo;

public class Student {
    private String stuid;
    private String stuname;
    private String college;
    private String profession;
    private String clazzname;

    public Student(String stuid, String stuname, String college, String profession, String clazzname) {
        this.stuid = stuid;
        this.stuname = stuname;
        this.college = college;
        this.profession = profession;
        this.clazzname = clazzname;
    }

    public Student() {
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getClazzname() {
        return clazzname;
    }

    public void setClazzname(String clazzname) {
        this.clazzname = clazzname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuid='" + stuid + '\'' +
                ", stuname='" + stuname + '\'' +
                ", college='" + college + '\'' +
                ", profession='" + profession + '\'' +
                ", clazzname='" + clazzname + '\'' +
                '}';
    }
}
