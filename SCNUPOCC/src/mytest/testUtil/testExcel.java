package mytest.testUtil;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class testExcel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try{
			File excelFile = new File("E:\\学生名单.xlsx"); //创建文件对象  
	        FileInputStream fis = new FileInputStream(excelFile); //文件流  
	        Workbook workbook = WorkbookFactory.create(fis); //这种方式 Excel 2003/2007/2010 都是可以处理的  
//	        int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量
	        Sheet sheet = workbook.getSheetAt(0);
	        int numberCol = 0; 	//学号列号
	        int nameCol = 0;	//姓名列号
	        for (int col = 0; col<sheet.getRow(0).getPhysicalNumberOfCells(); col++){
	        	String value = sheet.getRow(0).getCell(col).getStringCellValue();
	        	if ("学生学号".equals(value)||"学号".equals(value)){
	        		numberCol = col;
	        	}
	        	if ("学生姓名".equals(value)||"姓名".equals(value)){
	        		nameCol = col;
	        	}
	        }	        
//	        System.out.println("学号列："+numberCol);
//	        System.out.println("姓名列："+nameCol);	        
	        for (int rowNum = 1; rowNum<sheet.getPhysicalNumberOfRows(); rowNum++){
	        	Row row = sheet.getRow(rowNum);
	        	String name;
	        	String number;
//	        	//把数值型学号转换为字符串型
	        	if (row.getCell(numberCol).getCellType()==Cell.CELL_TYPE_NUMERIC){
	        		double d = row.getCell(numberCol).getNumericCellValue();
	        		DecimalFormat df=new DecimalFormat("0"); 
	        		number = df.format(d);
	        	}else number = row.getCell(numberCol).getStringCellValue();
	        	
	        	name = row.getCell(nameCol).getStringCellValue();
	        	System.out.println("学号："+number);
	        	System.out.println("姓名："+name);
	        	System.out.println();
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}







