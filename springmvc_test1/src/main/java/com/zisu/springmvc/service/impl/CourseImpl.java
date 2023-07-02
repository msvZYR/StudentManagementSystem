package com.zisu.springmvc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.mapper.CourseMapper;
import com.zisu.springmvc.mapper.RecordMapper;
import com.zisu.springmvc.pojo.Course;
import com.zisu.springmvc.pojo.Record;
import com.zisu.springmvc.pojo.Student;
import com.zisu.springmvc.service.CourseService;
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
public class CourseImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    RecordMapper recordMapper;
    @Override
    public List<Course> getAllCourse() {
        return courseMapper.getAllCourse();
    }

    @Override
    public List<Course> selectCourseByName(String coursename) {
        return courseMapper.selectCourseByName(coursename);
    }
    @Override
    public List<Course> selectCourseById(String courseid) {
        return courseMapper.selectCourseById(courseid);
    }

    @Override
    public int insertCourse(String admin,Course course) {
        Record record = new Record();
        record.setUid(admin);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setOid(course.getCourseid());
        record.setOname(course.getCoursename());
        record.setOperate("增加课程");
        recordMapper.insertRecord(record);

        return courseMapper.insertCourse(course);
    }

    @Override
    public int deleteCourseById(String admin,String courseid) {
        Record record = new Record();
        record.setUid(admin);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setOid(courseid);
        record.setOname(courseMapper.selectCourseNameById(courseid));
        record.setOperate("删除课程");
        recordMapper.insertRecord(record);
        return courseMapper.deleteCourseById(courseid);
    }

    @Override
    public int updateCourseTidById(Course course) {
        return courseMapper.updateCourseTidById(course);
    }
    @Override
    public int updateCourse(String admin,Course course) {
        Record record = new Record();
        record.setUid(admin);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setOid(course.getCourseid());
        record.setOname(course.getCoursename());
        record.setOperate("修改课程");
        recordMapper.insertRecord(record);

        return courseMapper.updateCourse(course);
    }
    @Override
    public Course getCourseByTIdAndCourseId(Course course) {
        return courseMapper.getCourseByTIdAndCourseId(course);
    }


    @Override
    public void InsertbatchCourses(String finalPath) {
        List<Course> courseList = excel2course(new File(finalPath));
        courseMapper.InsertbatchCourses(courseList);
    }

    private List<Course> excel2course(File xlsfile) {
        List<Course> courseList = new ArrayList<Course>();
        Course course = null;
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
                    course = new Course();
                    course.setCourseid(convertCellValueToString(row.getCell(0)));
                    course.setCoursename(convertCellValueToString(row.getCell(1)));
                    course.setCollege(convertCellValueToString(row.getCell(2)));
                    course.setWeek(convertCellValueToString(row.getCell(3)));
                    course.setCredit(Integer.parseInt(convertCellValueToString(row.getCell(4))));
                    course.setPeriod(Integer.parseInt(convertCellValueToString(row.getCell(5))));
                    courseList.add(course);
                }
                fs.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return courseList;
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
        List<Course> courseList = getAllCourse();
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        Workbook workbook = new HSSFWorkbook();
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = ((HSSFWorkbook) workbook).createSheet("courseinfo");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = ((HSSFWorkbook) workbook).createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        //style.setAlignment("ALIGN_CENTER"); // 创建一个居中格式
        //声明列对象
        HSSFCell cell11 = row.createCell(0);
        cell11.setCellValue("课程id");

        HSSFCell cell12 = row.createCell(1);
        cell12.setCellValue("课程名称");

        HSSFCell cell13 = row.createCell(2);
        cell13.setCellValue("开设学院");

        HSSFCell cell14 = row.createCell(3);
        cell14.setCellValue("周次");

        HSSFCell cell15 = row.createCell(4);
        cell15.setCellValue("学分");

        HSSFCell cell16 = row.createCell(5);
        cell16.setCellValue("学时");
        //将list记录写入excel文件
        int rows = 1;
        for (Course course:courseList) {
            //写入每一条记录
            row = sheet.createRow(rows);
            row.createCell(0).setCellValue(course.getCourseid());
            row.createCell(1).setCellValue(course.getCoursename());
            row.createCell(2).setCellValue(course.getCollege());
            row.createCell(3).setCellValue(course.getWeek());
            row.createCell(4).setCellValue(course.getCredit());
            row.createCell(5).setCellValue(course.getPeriod());
            rows++;
        }
        FileOutputStream os = new FileOutputStream(finalPath+File.separator+filename);
        ((HSSFWorkbook) workbook).write(os);
        os.close();
    }

    @Override
    public PageInfo<Course> getCoursePage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 6);
        //查询所有的学生信息
        List<Course> courseList= courseMapper.getAllCourse();
        //获取分页相关数据
        PageInfo<Course> page = new PageInfo<>(courseList, 3);
        return page;
    }
}
