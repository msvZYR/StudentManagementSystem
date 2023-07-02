package com.zisu.springmvc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zisu.springmvc.mapper.RecordMapper;
import com.zisu.springmvc.mapper.ScheduleMapper;
import com.zisu.springmvc.pojo.Record;
import com.zisu.springmvc.pojo.Schedule;
import com.zisu.springmvc.pojo.Student;
import com.zisu.springmvc.service.ScheduleService;
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
public class ScheduleImpl implements ScheduleService {
    @Autowired
    public ScheduleMapper scheduleMapper;
    @Autowired
    RecordMapper recordMapper;
    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleMapper.getAllSchedule();
    }



    @Override
    public int insertSchedule(String admin,Schedule schedule) {
        Record record = new Record();
        record.setUid(admin);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setOid(String.valueOf(schedule.getScheduleid()));
        record.setOname(schedule.getClazzname());
        record.setOperate("增加排课");
        recordMapper.insertRecord(record);

        return scheduleMapper.insertSchedule(schedule);
    }

    @Override
    public List<Schedule> selectScheduleByClazzName(String clazzname) {
        return scheduleMapper.selectScheduleByClazzName(clazzname);
    }

    @Override
    public List<Schedule> selectScheduleByScheduleId(String scheduleid) {
        return scheduleMapper.selectScheduleByScheduleId(scheduleid);
    }

    @Override
    public int deleteStudentById(String admin,String scheduleid) {
        Record record = new Record();
        record.setUid(admin);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setOid(scheduleid);
        record.setOname(scheduleMapper.selectClazzNameByScheduleId(scheduleid));
        record.setOperate("删除排课");
        recordMapper.insertRecord(record);

        return scheduleMapper.deleteStudentById(scheduleid);
    }

    @Override
    public int updateSchedule(String admin,Schedule schedule) {
        Record record = new Record();
        record.setUid(admin);
        record.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setOid(String.valueOf(schedule.getScheduleid()));
        record.setOname(schedule.getClazzname());
        record.setOperate("更新排课");
        recordMapper.insertRecord(record);

        return scheduleMapper.updateSchedule(schedule);
    }

    @Override
    public void InsertbatchSchedules(String finalPath) {
        List<Schedule> scheduleList = excel2schedule(new File(finalPath));
        scheduleMapper.InsertbatchSchedules(scheduleList);
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
    private List<Schedule> excel2schedule(File xlsfile) {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        Schedule schedule = null;
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
                    schedule = new Schedule();
//                    schedule.setScheduleid(Integer.parseInt(convertCellValueToString(row.getCell(0))));
                    schedule.setCoursename(convertCellValueToString(row.getCell(1)));
                    schedule.setClazzname(convertCellValueToString(row.getCell(2)));
                    schedule.setTname(convertCellValueToString(row.getCell(3)));
                    schedule.setRoomname(convertCellValueToString(row.getCell(4)));
                    scheduleList.add(schedule);
                }
                fs.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return scheduleList;
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
        List<Schedule> scheduleList = getAllSchedule();
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        Workbook workbook = new HSSFWorkbook();
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = ((HSSFWorkbook) workbook).createSheet("scheduleinfo");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = ((HSSFWorkbook) workbook).createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        //style.setAlignment("ALIGN_CENTER"); // 创建一个居中格式
        //声明列对象
        HSSFCell cell11 = row.createCell(0);
        cell11.setCellValue("排课id");

        HSSFCell cell12 = row.createCell(1);
        cell12.setCellValue("课程名称");

        HSSFCell cell13 = row.createCell(2);
        cell13.setCellValue("班级名称");

        HSSFCell cell14 = row.createCell(3);
        cell14.setCellValue("教师名称");

        HSSFCell cell15 = row.createCell(4);
        cell15.setCellValue("教室");
        //将list记录写入excel文件
        int rows = 1;
        for (Schedule schedule:scheduleList) {
            //写入每一条记录
            row = sheet.createRow(rows);
            row.createCell(0).setCellValue(schedule.getScheduleid());
            row.createCell(1).setCellValue(schedule.getCoursename());
            row.createCell(2).setCellValue(schedule.getClazzname());
            row.createCell(3).setCellValue(schedule.getTname());
            row.createCell(4).setCellValue(schedule.getRoomname());
            rows++;
        }
        FileOutputStream os = new FileOutputStream(finalPath+File.separator+filename);
        ((HSSFWorkbook) workbook).write(os);
        os.close();
    }

    @Override
    public PageInfo<Schedule> getSchedulePage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 6);
        //查询所有的学生信息
        List<Schedule> scheduleList = scheduleMapper.getAllSchedule();
        //获取分页相关数据
        PageInfo<Schedule> page = new PageInfo<>(scheduleList, 5);
        return page;
    }
}
