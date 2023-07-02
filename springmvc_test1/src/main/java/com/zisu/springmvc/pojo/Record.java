package com.zisu.springmvc.pojo;

import java.util.Date;

public class Record {
    private int rid;
    private String uid;
    private String operate;
    private String oid;
    private String oname;
    private String date;

    public Record(int rid, String uid, String operate, String oid, String oname, String date) {
        this.rid = rid;
        this.uid = uid;
        this.operate = operate;
        this.oid = oid;
        this.oname = oname;
        this.date = date;
    }

    public Record() {
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Record{" +
                "rid=" + rid +
                ", uid='" + uid + '\'' +
                ", operate='" + operate + '\'' +
                ", oid='" + oid + '\'' +
                ", oname='" + oname + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
