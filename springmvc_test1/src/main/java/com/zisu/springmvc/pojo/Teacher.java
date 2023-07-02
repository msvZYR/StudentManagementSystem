package com.zisu.springmvc.pojo;

public class Teacher {
    private String tname;
    private String tid;
    private String gender;
    private String trank;

    public Teacher(String tname, String tid, String gender, String trank) {
        this.tname = tname;
        this.tid = tid;
        this.gender = gender;
        this.trank = trank;
    }

    public Teacher() {
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTrank() {
        return trank;
    }

    public void setTrank(String trank) {
        this.trank = trank;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tname='" + tname + '\'' +
                ", tid='" + tid + '\'' +
                ", gender='" + gender + '\'' +
                ", trank='" + trank + '\'' +
                '}';
    }
}
