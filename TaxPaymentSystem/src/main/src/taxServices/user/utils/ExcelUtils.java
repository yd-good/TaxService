/*
信息:
*/
package taxServices.user.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import taxServices.user.entity.User;
import yd.itcast.core.utils.DatetoString;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static void exportExcelUtils(List<User> userList, ServletOutputStream outputStream) throws Exception{
        //创建工作簿
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建单元格范围地址对象
        CellRangeAddress cellRangeAddress= new CellRangeAddress(0,0,0,4);
        //获取样式
        HSSFCellStyle headStyle=createCellStyle(workbook,(short)16);
        //创建工作表
        HSSFSheet sheet=workbook.createSheet("用户列表");
        sheet.addMergedRegion(cellRangeAddress);//加载单元格范围地址
        sheet.setDefaultColumnWidth(20);
        //创建行
        HSSFRow headRow=sheet.createRow(0);
        HSSFCell headCell=headRow.createCell(0);
        headCell.setCellStyle(headStyle);
        headCell.setCellValue("用户列表");

        HSSFCellStyle titleStyle=createCellStyle(workbook,(short)13);
        HSSFRow titleRow=sheet.createRow(1);
        String[] titleContent={"用户名","账号","所属部门","性别","电子邮箱","电话","生日"};
        HSSFCell titleCell=null;
        for (int i=0;i<titleContent.length;i++){
            titleCell=titleRow.createCell(i);
            titleCell.setCellStyle(titleStyle);
            titleCell.setCellValue(titleContent[i]);
        }
        if (userList!=null){
            for (int i=0;i<userList.size();i++){
                HSSFRow row=sheet.createRow(i+2);
                HSSFCell cell10=row.createCell(0);
                cell10.setCellValue(userList.get(i).getName());
                HSSFCell cell11=row.createCell(1);
                cell11.setCellValue(userList.get(i).getAccount());
                HSSFCell cell12=row.createCell(2);
                cell12.setCellValue(userList.get(i).getDept());
                HSSFCell cell13=row.createCell(3);
                cell13.setCellValue(userList.get(i).isGender()?"男":"女");
                HSSFCell cell14=row.createCell(4);
                cell14.setCellValue(userList.get(i).getEmail());
                HSSFCell cell15=row.createCell(5);
                cell15.setCellValue(userList.get(i).getMobile());
                HSSFCell cell16=row.createCell(6);
                cell16.setCellValue(DatetoString.dateCovertoString(userList.get(i).getBirthday()));
            }
        }
        //输出
        workbook.write(outputStream);
    }
    public static HSSFCellStyle  createCellStyle(HSSFWorkbook workbook,short fontSize){
        HSSFCellStyle style=workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont font=workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        font.setFontHeightInPoints(fontSize);
        style.setFont(font);
        return style;

    }

    public static List<User> importExcel(File userExcel, String userExcelFileName) {
        Boolean is03Excel=userExcelFileName.matches("^.+\\.(?i)(xls)$");
        try {
            FileInputStream inputStream=new FileInputStream(userExcel);
            //读取工作簿
            Workbook workbook=is03Excel?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
            //读取第一个工作表
            Sheet sheet=workbook.getSheetAt(0);
            //读取行
            if (sheet.getPhysicalNumberOfRows()>2){
                List<User> userList=new ArrayList<User>();
                User user=null;
                for (int k=2;k<sheet.getPhysicalNumberOfRows();k++){
                    user=new User();
                    Row row=sheet.getRow(k);
                    Cell cell1=row.getCell(0);
                    user.setName(cell1.getStringCellValue());
                    Cell cell2=row.getCell(1);
                    user.setAccount(cell2.getStringCellValue());
                    Cell cell3=row.getCell(2);
                    user.setDept(cell3.getStringCellValue());
                    Cell cell4=row.getCell(3);
                    user.setGender(cell4.getStringCellValue().equals("男"));
                    Cell cell6=row.getCell(4);
                    user.setEmail(cell6.getStringCellValue());
                    Cell cell5=row.getCell(5);
                    //手机号
                    String mobile="";
                    try {
                        mobile=cell5.getStringCellValue();
                    }catch (Exception e){
                        double dMobile=cell5.getNumericCellValue();
                        mobile= BigDecimal.valueOf(dMobile).toString();
                    }
                    user.setMobile(mobile);
                    Cell cell7=row.getCell(6);
                    if (cell7.getDateCellValue()!=null){ //获取日期类型
                        user.setBirthday(cell7.getDateCellValue());
                    }
                    user.setPassword("123456");
                    user.setState(User.USER_SATAE_VALID);
                    userList.add(user);
                }
                return userList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
