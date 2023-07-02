package com.zisu.springmvc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.mapper.RecordMapper;
import com.zisu.springmvc.mapper.StudentMapper;
import com.zisu.springmvc.pojo.Course;
import com.zisu.springmvc.pojo.Record;
import com.zisu.springmvc.pojo.Schedule;
import com.zisu.springmvc.pojo.Student;
import com.zisu.springmvc.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentImpl implements StudentService {
    @Autowired
    public StudentMapper studentMapper;
    @Autowired
    RecordMapper recordMapper;

    @Override
    public List<Student> getAllStudent() {
        return studentMapper.getAllStudent();
    }

    @Override
    public List<Student> selectStudentByName(String stuname) {
        return studentMapper.selectStudentByName(stuname);
    }
    @Override
    public List<Student> selectStudentById(String stuid) {
        return studentMapper.selectStudentById(stuid);
    }

    @Override
    public List<Student> selectStudentByClazz(String clazzname) {
        return studentMapper.selectStudentByClazz(clazzname);
    }

    @Override
    public int insertStudent(String admin,Student student) {
        Record record = new Record();
        record.setUid(admin);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setOid(student.getStuid());
        record.setOname(student.getStuname());
        record.setOperate("增加学生");
        recordMapper.insertRecord(record);

        return studentMapper.insertStudent(student);
    }

    @Override
    public int deleteStudentById(String admin,String stuid) {

        Record record = new Record();
        record.setUid(admin);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setOid(stuid);
        record.setOname(studentMapper.selectStudentNameById(stuid));
        record.setOperate("删除学生");
        recordMapper.insertRecord(record);

        return studentMapper.deleteStudentById(stuid);
    }

    @Override
    public int updateStudentNameById(Student student) {
        return studentMapper.updateStudentNameById(student);
    }
    @Override
    public int updateStudent(String admin,Student student){
        int key=studentMapper.updateStudent(student);

        Record record = new Record();
        record.setUid(admin);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setOid(student.getStuid());
        record.setOname(student.getStuname());
        record.setOperate("修改学生");
        recordMapper.insertRecord(record);

//        System.out.println("resule========"+studentMapper.selectStudentById(stuid));
        return key;
    }
    @Override
    public List<Course> getCourseByStuId(String stuid) {
        return studentMapper.getCourseByStuId(stuid);
    }

    @Override
    public List<Student> getStudentByTIdAndCourseId(Schedule schedule) {
        return studentMapper.getStudentByTIdAndCourseId(schedule);
    }

    //（2）获取学生的分页信息
    @Override
    public PageInfo<Student> getStudentPage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 6);
        //查询所有的学生信息
        List<Student> studentList = studentMapper.getAllStudent();
        //获取分页相关数据
        PageInfo<Student> page = new PageInfo<>(studentList, 5);
        return page;
    }

    @Override
    public void InsertbatchStudents(String finalPath) {
        List<Student> studentList = excel2student(new File(finalPath));
        studentMapper.InsertbatchStudents(studentList);
    }


    private List<Student> excel2student(File xlsfile) {
        List<Student> studentList = new ArrayList<Student>();
        Student student = null;
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
                    student = new Student();
                    student.setStuid(convertCellValueToString(row.getCell(0)));
                    student.setStuname(convertCellValueToString(row.getCell(1)));
                    student.setClazzname(convertCellValueToString(row.getCell(2)));
                    student.setCollege(convertCellValueToString(row.getCell(3)));
                    student.setProfession(convertCellValueToString(row.getCell(4)));
                    studentList.add(student);
                }
                fs.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentList;
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
        List<Student> studentList = getAllStudent();
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        Workbook workbook = new HSSFWorkbook();
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = ((HSSFWorkbook) workbook).createSheet("studentinfo");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = ((HSSFWorkbook) workbook).createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        //style.setAlignment("ALIGN_CENTER"); // 创建一个居中格式
        //声明列对象
        HSSFCell cell11 = row.createCell(0);
        cell11.setCellValue("学号");

        HSSFCell cell12 = row.createCell(1);
        cell12.setCellValue("姓名");

        HSSFCell cell13 = row.createCell(2);
        cell13.setCellValue("班级");

        HSSFCell cell14 = row.createCell(3);
        cell14.setCellValue("学院");

        HSSFCell cell15 = row.createCell(4);
        cell15.setCellValue("专业");
        //将list记录写入excel文件
        int rows = 1;
        for (Student student:studentList) {
            //写入每一条记录
            row = sheet.createRow(rows);
            row.createCell(0).setCellValue(student.getStuid());
            row.createCell(1).setCellValue(student.getStuname());
            row.createCell(2).setCellValue(student.getClazzname());
            row.createCell(3).setCellValue(student.getCollege());
            row.createCell(4).setCellValue(student.getProfession());
            rows++;
        }
        FileOutputStream os = new FileOutputStream(finalPath+File.separator+filename);
        ((HSSFWorkbook) workbook).write(os);
        os.close();
    }
}
