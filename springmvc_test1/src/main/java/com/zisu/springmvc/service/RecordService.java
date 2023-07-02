package com.zisu.springmvc.service;


import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.pojo.Record;
import com.zisu.springmvc.pojo.Student;

import java.util.List;

public interface RecordService {
    List<Record> getAllRecord();
    List<Record> selectRecordByUid(String uid);
    PageInfo<Record> getRecordPage(Integer pageNum);
}
