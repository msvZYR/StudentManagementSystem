package com.zisu.springmvc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.mapper.RecordMapper;
import com.zisu.springmvc.mapper.UserMapper;
import com.zisu.springmvc.pojo.Record;
import com.zisu.springmvc.pojo.Student;
import com.zisu.springmvc.pojo.User;
import com.zisu.springmvc.service.RecordService;
import com.zisu.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;


    @Override
    public List<Record> getAllRecord() {
        return recordMapper.getAllRecord();
    }

    @Override
    public List<Record> selectRecordByUid(String uid) {
        return recordMapper.selectRecordByUid(uid);
    }

    @Override
    public PageInfo<Record> getRecordPage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 6);
        //查询所有的学生信息
        List<Record> recordList = recordMapper.getAllRecord();
        //获取分页相关数据
        PageInfo<Record> page = new PageInfo<>(recordList, 5);
        return page;
    }
}
