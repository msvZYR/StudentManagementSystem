package com.zisu.springmvc.mapper;

import com.zisu.springmvc.pojo.Clazz;
import com.zisu.springmvc.pojo.Record;
import com.zisu.springmvc.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RecordMapper {
    List<Record> getAllRecord();
    List<Record> selectRecordByUid(String uid);

    @Insert("insert into t_record values (null,#{uid},#{operate},#{oid},#{oname},#{date})")
//    @Options(useGeneratedKeys = true,keyProperty = "clazzid")
    int insertRecord(Record record);
}
