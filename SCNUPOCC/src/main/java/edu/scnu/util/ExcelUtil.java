package main.java.edu.scnu.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
        sheet.addMergedRegion(new org.apache.poi.ss.util.Region(0,(short)0,0,(short)4)); //合并列
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
}
