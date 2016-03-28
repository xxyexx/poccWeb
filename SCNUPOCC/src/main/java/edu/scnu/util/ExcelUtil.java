package main.java.edu.scnu.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	/**
	 * 写入excel表
	 * @param title 标题
	 * @param header 表格第一行标题
	 * @param list List<String[]>
	 * @return InputStream 
	 */
	@SuppressWarnings("deprecation")
	public static InputStream writeExcel(String title,String []header,List<String[]> list){
		//构建excel表格
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow row = sheet.createRow(0);//创建第一行
        HSSFCell cell = row.createCell(0);//第一列
        cell.setCellValue(title);//设置标题
        HSSFCellStyle style = workbook.createCellStyle(); //创建样式
        HSSFFont font = workbook.createFont(); //创建字体样式
                font.setFontHeight((short)(20*20)); //字体
                font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); //加粗
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中
        style.setFont(font); //设置字体样式  
        cell.setCellStyle(style); //设置样式
        sheet.addMergedRegion(new CellRangeAddress(0,(short)0,0,(short)4));//合并列
        //构造列表头
        row = sheet.createRow(1);//创建第一行
        for(int i=0;i<header.length;i++){
        	cell = row.createCell(i); //创建第i+1列
        	cell.setCellValue(new HSSFRichTextString(header[i]));
        }
        //封装数据到excel 
        for(int i=0;i<list.size();i++){
        	row=sheet.createRow(i+2);//创建第i+1行
        	String []value = new String[header.length];
        	value = list.get(i);//获取当前行
        	for(int j=0;j<value.length;j++){
        		cell = row.createCell(j); //创建第i+1列
        		cell.setCellValue(new HSSFRichTextString(value[j]));//设值
        	}
        }
        //写入输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
                workbook.write(baos);//写入
        } catch (IOException e) {
                e.printStackTrace();
        }
        byte[] ba = baos.toByteArray(); 
        ByteArrayInputStream bais = new ByteArrayInputStream(ba); 
        return bais;
	}
	
	/**
	 * 读取excel表
	 * @param titleNO 标题所在行数（从0开始）
	 * @param is excel文件输入流
	 * @param titles 若干个标题 如   "姓名","学号"
	 * @return Map<> 其中key为标题String，value为对应列表List<String>
	 */
	public static Map<String,List<String>> readExcel(int titleNO,InputStream is,String... titles){
		
//		FileInputStream fis = new FileInputStream(is);
		Map<String,List<String>> map = null;
		try {
			Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的
			Sheet sheet = workbook.getSheetAt(0);
			int tnum = titles.length; //标题数
			int[] titlesNum = new int[tnum];			
			//遍历所有标题,找到目标标题所在列
			for (int i=0; i<tnum; i++){
				String target=titles[i];
				for (int col = 0; col<sheet.getRow(titleNO).getPhysicalNumberOfCells(); col++){
					titlesNum[i] = -1;
		        	String value = sheet.getRow(0).getCell(col).getStringCellValue();
		        	if (target.equals(value)){
		        		titlesNum[i] = col;
		        		break;
		        	}
		        }
			}			
			//遍历所有标题，转换数据
			map = new HashMap<String,List<String>>();
        	for (int i = 0; i<tnum; i++){
        		if (titlesNum[i]<0) continue;
        		List<String> list = new ArrayList<String>();
        		for (int rowNum = titleNO+1; rowNum<sheet.getPhysicalNumberOfRows(); rowNum++){
        			Row row = sheet.getRow(rowNum);
        			Cell cell = row.getCell(titlesNum[i]);
        			if (cell==null) break;
        			String result = null;        			
        			if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
        				//把数值型数据转换为字符串型
    	        		double d = cell.getNumericCellValue();
    	        		DecimalFormat df=new DecimalFormat("0"); 
    	        		result = df.format(d);
    	        	}else result = cell.getStringCellValue();
        			list.add(result);        		
        		}
        		map.put(titles[i], list);
        	}						
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return map;		
	}
	
}
