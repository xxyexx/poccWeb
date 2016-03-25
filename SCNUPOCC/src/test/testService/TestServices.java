package test.testService;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.java.edu.scnu.entity.Experiment;
import main.java.edu.scnu.entity.HWSubmit;
import main.java.edu.scnu.entity.Homework;
import main.java.edu.scnu.entity.SiteMsg;
import main.java.edu.scnu.entity.User;
import main.java.edu.scnu.service.ExperimentService;
import main.java.edu.scnu.service.HomeworkService;
import main.java.edu.scnu.service.ProvinceService;
import main.java.edu.scnu.service.SiteMsgService;
import main.java.edu.scnu.util.FileUtil;
import main.java.edu.scnu.util.PoccManager;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestServices {

	@Test
	public void test() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//ProvinceService provinceService = (ProvinceService) ctx.getBean("provinceService");
		//SchoolService schoolService = (SchoolService) ctx.getBean("schoolService");
		//Province province = provinceService.get(Province.class, 10);
		//Map<Integer, String> provinceMap = provinceService.getProvinceMap();
		//System.out.println(provinceMap.size());
		//List<School> schoolList = schoolService.
		
//		HomeworkService homeworkService =(HomeworkService) ctx.getBean("homeworkService");
//		List<Homework> newHWlist = homeworkService.getUnfinishedHW("1", "20122004000");
//		for (Homework homework : newHWlist) {
//			System.out.println(homework.getClassID());
//		}
//		SiteMsgService siteMsgService =(SiteMsgService) ctx.getBean("siteMsgService");
//		User user = new User();
//		user.setAcctID("20122004000");
//		user.setUserType("student");
//		user.setManagerID("2012000");
//		user.setAcctTag("华南师范大学");
//		user.setClassID("1");
//		List<SiteMsg> List = siteMsgService.getUnreadMsg(user);
//		for (SiteMsg siteMsg : List) {
//			System.out.println(siteMsg.getMessage());
//		}
		HomeworkService homeworkService =(HomeworkService) ctx.getBean("homeworkService");
		//该作业已完成学生列表
		List<HWSubmit> hwsList =  homeworkService.getfinishedHWSList(1);
		List<File> fileList = new ArrayList<File>();
		for(int i=0;i<hwsList.size();i++){
			String fileUrl = hwsList.get(i).getFileURL();//文件保存地址
			File file = new File(PoccManager.ROOT_DIR+fileUrl);
			System.out.println("file.getName()="+file.getName());
			fileList.add(file);
		}
		try {
			String zipFileUrl = FileUtil.createZip(fileList, "1班作业压缩文件.zip",PoccManager.ROOT_DIR+PoccManager.Homework_ZipFile_dir);
			System.out.println("zipFileUrl="+zipFileUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
