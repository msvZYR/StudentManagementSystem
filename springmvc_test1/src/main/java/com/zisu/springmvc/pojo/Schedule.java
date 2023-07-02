package com.zisu.springmvc.pojo;

import java.util.List;

public class Schedule {
    private int scheduleid;
    private String coursename;
    private String clazzname;
    private String tname;
    private String roomname;

    public Schedule(int scheduleid, String coursename, String clazzname, String tname, String roomname) {
        this.scheduleid = scheduleid;
        this.coursename = coursename;
        this.clazzname = clazzname;
        this.tname = tname;
        this.roomname = roomname;
    }

    public Schedule() {
    }

    public int getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(int scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getClazzname() {
        return clazzname;
    }

    public void setClazzname(String clazzname) {
        this.clazzname = clazzname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleid=" + scheduleid +
                ", coursename='" + coursename + '\'' +
                ", clazzname='" + clazzname + '\'' +
                ", tname='" + tname + '\'' +
                ", roomname='" + roomname + '\'' +
                '}';
    }
}
