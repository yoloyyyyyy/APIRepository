package com.qa.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelHandle {
	public Object[][] readExcel(String filepath, int sheetNum){
        String fileType = filepath.substring(filepath.lastIndexOf(".") + 1);
        FileInputStream fis ;
        Workbook workbook ;
        try {
            fis = new FileInputStream(filepath);
            if(fileType.equals("xls")){
                workbook = new HSSFWorkbook(fis);
            }else if(fileType.equals("xlsx")){
                workbook = new XSSFWorkbook(fis);
            }else {
                return null;
            }
            //读取参数的页数
            Sheet sheet =  workbook.getSheetAt(sheetNum);
            //获取物理总行数，剔除空格
            int rowNum = sheet.getPhysicalNumberOfRows();
            //获取总列数
            int cellNum = sheet.getRow(0).getLastCellNum();
//           创建行列相对应的二维数组对象
            Object[][] obj = new Object[rowNum-1][cellNum];
            for (int i=0;i<rowNum-1;i++){
                if(null == sheet.getRow(i+1) ||"" .equals(sheet.getRow(i+1))){
                    continue;
                }
                for (int j=0;j<cellNum;j++){
                    if(null == sheet.getRow(i+1).getCell(j)|| "".equals(sheet.getRow(i+1).getCell(j))){
                        continue;
                    }
                    Cell cell = sheet.getRow(i+1).getCell(j);
                    cell.setCellType(CellType.STRING);
                    obj[i][j] = cell.getStringCellValue();
//                    System.out.println(obj[i][j]);
                }
            }
            return  obj;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null; 
    }

/*	public static void main(String[] args) throws FileNotFoundException {
//        String path = System.getProperty("user.dir")+"\\eztest\\src\\main\\java\\data\\auto01.xlsx";
		String path = "C:\\Users\\Administrator\\git\\APIRepository\\API_AutoFramework_Demo\\src\\main\\java\\com\\qa\\data\\auto01.xlsx";
        ExcelHandle handle = new ExcelHandle();
        Object[][] obj = handle.readExcel(path,0);
        System.out.println(obj[0][0]);
     }*/

}
