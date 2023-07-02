package com.zisu.springmvc.pojo;

public class Course {
    private String courseid;
    private String coursename;
    private String college;
    private String week;
    private int credit;
    private int period;

    public Course(String courseid, String coursename, String college, String week, int credit, int period) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.college = college;
        this.week = week;
        this.credit = credit;
        this.period = period;
    }

    public Course() {
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseid='" + courseid + '\'' +
                ", coursename='" + coursename + '\'' +
                ", college='" + college + '\'' +
                ", week='" + week + '\'' +
                ", credit=" + credit +
                ", period=" + period +
                '}';
    }
}
