package com.zisu.springmvc.pojo;

import java.util.List;

public class Clazz {
    private String clazzid;
    private String clazzname;
    private String clazztname;

    public Clazz(String clazzid, String clazzname, String clazztname) {
        this.clazzid = clazzid;
        this.clazzname = clazzname;
        this.clazztname = clazztname;
    }

    public Clazz() {
    }

    public String getClazzid() {
        return clazzid;
    }

    public void setClazzid(String clazzid) {
        this.clazzid = clazzid;
    }

    public String getClazzname() {
        return clazzname;
    }

    public void setClazzname(String clazzname) {
        this.clazzname = clazzname;
    }

    public String getClazztname() {
        return clazztname;
    }

    public void setClazztname(String clazztname) {
        this.clazztname = clazztname;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "clazzid='" + clazzid + '\'' +
                ", clazzname='" + clazzname + '\'' +
                ", clazztname='" + clazztname + '\'' +
                '}';
    }
}
