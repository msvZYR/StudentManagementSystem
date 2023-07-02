package com.zisu.springmvc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.mapper.ClazzMapper;
import com.zisu.springmvc.mapper.RecordMapper;
import com.zisu.springmvc.pojo.Clazz;
import com.zisu.springmvc.pojo.Record;
import com.zisu.springmvc.pojo.Schedule;
import com.zisu.springmvc.pojo.Student;
import com.zisu.springmvc.service.ClazzService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ClazzImpl implements ClazzService {
    @Autowired
    public ClazzMapper clazzMapper;
    @Autowired
    RecordMapper recordMapper;
    @Override
    public List<Clazz> getAllClass() {
        return clazzMapper.getAllClass();
    }
    @Override
    public List<Clazz> selectClassByName(String clazzname) {
        return clazzMapper.selectClassByName(clazzname);
    }

    @Override
    public List<Clazz> selectClassById(String clazzid) {
        return clazzMapper.selectClassById(clazzid);
    }

    @Override
    public String selectClassNameById(String clazzid) {
        return clazzMapper.selectClassNameById(clazzid);
    }

    @Override
    public int insertClass(String admin,Clazz clazz) {

        Record record = new Record();
        record.setUid(admin);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setOid(clazz.getClazzid());
        record.setOname(clazz.getClazzname());
        record.setOperate("插入班级");
        recordMapper.insertRecord(record);

        return clazzMapper.insertClass(clazz);
    }
    @Override
    public int deleteClassById(String admin,String clazzid) {
        Record record = new Record();
        record.setUid(admin);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setOid(clazzid);
        record.setOname(clazzMapper.selectClassNameById(clazzid));
        record.setOperate("删除班级");
        recordMapper.insertRecord(record);

        return clazzMapper.deleteClassById(clazzid);
    }
    @Override
    public int updateClassNameById(Clazz clazz) {
        return clazzMapper.updateClassNameById(clazz);
    }

    @Override
    public int updateClass(String admin,Clazz clazz) {

        Record record = new Record();
        record.setUid(admin);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setOid(clazz.getClazzid());
        record.setOname(clazz.getClazzname());
        record.setOperate("更新班级");
        recordMapper.insertRecord(record);

        return clazzMapper.updateClass(clazz);
    }

    @Override
    public List<Student> selectStudentsByClassId(String clazzid) {return clazzMapper.selectStudentsByClassId(clazzid);}
    @Override
    public List<Schedule> selectScheduleByClassId(String clazzid) {return clazzMapper.selectScheduleByClassId(clazzid);}
    @Override
    public List<Clazz> getClassByTIdAndCourseId(Schedule schedule) {return clazzMapper.getClassByTIdAndCourseId(schedule);}

    @Override
    public void InsertbatchClazzs(String finalPath) {
        List<Clazz> clazzList = excel2clazz(new File(finalPath));
        clazzMapper.InsertbatchClazzs(clazzList);
    }

    //（2）获取学生的分页信息
//    @Override
//    public PageInfo<Student> getStudentPage(Integer pageNum) {
//        //开启分页功能
//        PageHelper.startPage(pageNum, 4);
//        //查询所有的学生信息
//        List<Student> studentList = studentMapper.getAllStudent();
//        //获取分页相关数据
//        PageInfo<Student> page = new PageInfo<>(studentList, 5);
//        return page;
//    }
    private List<Clazz> excel2clazz(File xlsfile) {
        List<Clazz> clazzList = new ArrayList<Clazz>();
        Clazz clazz = null;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(xlsfile));
            HSSFWorkbook workbook = new HSSFWorkbook(fs);
            //获取第一个sheet页
            CellType cellType ;

            HSSFSheet sheet = workbook.getSheetAt(0);
            if (sheet != null) {
                for (int rowNumber = 1; rowNumber < sheet.getLastRowNum(); rowNumber++) {
                    HSSFRow row = sheet.getRow(rowNumber);
                    if (row == null) {
                        continue;
                    }
                    clazz = new Clazz();
                    clazz.setClazzid(convertCellValueToString(row.getCell(0)));
                    clazz.setClazzname(convertCellValueToString(row.getCell(1)));
                    clazz.setClazztname(convertCellValueToString(row.getCell(2)));
                    clazzList.add(clazz);
                }
                fs.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return clazzList;
    }

    private static String convertCellValueToString(Cell cell) {
        if (null == cell) {
            return null;
        }
        String returnValue = null;
        switch (cell.getCellType()) {
            case STRING:  //字符串
                returnValue = cell.getStringCellValue();
                break;
            case NUMERIC: //数字
                double numericCellValue = cell.getNumericCellValue();
                //清除格式，单元格不设置数字格式
                //科学计数法的数字全部显示
                /*cell.setCellStyle(null);
                returnValue = Double.toString(numericCellValue);*/
                boolean isInteger = isIntegerForDouble(numericCellValue);
                if (isInteger) {
                    DecimalFormat df = new DecimalFormat("0");
                    returnValue = df.format(numericCellValue);
                } else {
                    returnValue = Double.toString(numericCellValue);
                }
                break;
            case BOOLEAN: //布尔
                boolean booleanCellValue = cell.getBooleanCellValue();
                returnValue = Boolean.toString(booleanCellValue);
                break;
            case BLANK: //空值
                break;
            case FORMULA: //公式
                cell.getCellFormula();
                break;
            case ERROR: //故障
                break;
            default:
                break;
        }
        return returnValue;
    }
    public static boolean isIntegerForDouble(Double num) {
        double eqs = 1e-10; //精度范围
        return num - Math.floor(num) < eqs;
    }
    //导出为excel文件
    @Override
    public void saveasexcel(String finalPath,String filename) throws IOException {
        List<Clazz> clazzList= getAllClass();
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        Workbook workbook = new HSSFWorkbook();
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = ((HSSFWorkbook) workbook).createSheet("clazzinfo");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = ((HSSFWorkbook) workbook).createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        //style.setAlignment("ALIGN_CENTER"); // 创建一个居中格式
        //声明列对象
        HSSFCell cell11 = row.createCell(0);
        cell11.setCellValue("班级id");

        HSSFCell cell12 = row.createCell(1);
        cell12.setCellValue("班级名称");

        HSSFCell cell13 = row.createCell(2);
        cell13.setCellValue("班主任");
        //将list记录写入excel文件
        int rows = 1;
        for (Clazz clazz:clazzList) {
            //写入每一条记录
            row = sheet.createRow(rows);
            row.createCell(0).setCellValue(clazz.getClazzid());
            row.createCell(1).setCellValue(clazz.getClazzname());
            row.createCell(2).setCellValue(clazz.getClazztname());
            rows++;
        }
        FileOutputStream os = new FileOutputStream(finalPath+File.separator+filename);
        ((HSSFWorkbook) workbook).write(os);
        os.close();
    }

    @Override
    public PageInfo<Clazz> getClazzPage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 4);
        //查询所有的学生信息
        List<Clazz> clazzList = clazzMapper.getAllClass();
        //获取分页相关数据
        PageInfo<Clazz> page = new PageInfo<>(clazzList, 5);
        return page;
    }
}
