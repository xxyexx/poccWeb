package main.java.edu.scnu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	/**
	 * 保存文件到本地
	 * @param srcFile 要保存的文件
	 * @param root_dir	文件保存的根目录
	 * @param path	文件保存到根目录下的文件夹
	 * @param filename 文件保存到本地的名称
	 * @return 文件保存url=path+filename
	 */
	public static String saveFile(File srcFile,String root_dir,String path,String filename){
		//保存新文件到硬盘,为了防止文件名重复,保存时间+文件名称
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		filename = sdf.format(new Date())+filename;
		File savefile = new File(new File(root_dir+path),filename);
		if (!savefile.getParentFile().exists())
			savefile.getParentFile().mkdirs();
        try {
			FileUtils.copyFile(srcFile, savefile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件保存失败");
		}
		return path+filename;
	}
	/**
	 * 删除文件
	 * @param fileURL 文件绝对路径
	 * @return 
	 */
	public static boolean deleteFile(String fileURL){
		//删除旧文件
		File f = new File(fileURL);
		if(f.exists()){
			f.delete();
			return true;
		}else{
			System.out.println("文件不存在");
			return false;
		}
	}
	/**
	 * 打包zip压缩文件
	 * @param fileList 传入的文件list
	 * @param zipFilename 压缩文件名称
	 * @param zipPath 压缩文件保存文件夹
	 * @return 压缩文件所在路径
	 * @throws IOException 
	 */
	public static String createZip(List<File> fileList,String zipFilename,String zipPath) throws IOException{
	    File file = new File(zipPath);
	    System.err.println(zipPath);
	    File zipFile = new File(file,zipFilename);
	    if (!zipFile.getParentFile().exists())
	    	zipFile.getParentFile().mkdirs();
	    InputStream input = null;
	    ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
	    //设置zip文件注释
	    //zipOut.setComment(file.getName());
	    if (file.isDirectory()) {
	    	for(int i=0;i<fileList.size();i++){
	    	    input = new FileInputStream(fileList.get(i));
	    	    zipOut.putNextEntry(new ZipEntry(fileList.get(i).getName()));
	    	    int temp = 0;
                while ((temp = input.read()) != -1) {
                    zipOut.write(temp);
                }
                input.close();
	    	}
	    	zipOut.close();
	    }
	    
		return zipPath+zipFilename;
	}
}
