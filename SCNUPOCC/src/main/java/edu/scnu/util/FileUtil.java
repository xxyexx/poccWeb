package main.java.edu.scnu.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
