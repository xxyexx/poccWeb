package main.java.edu.scnu.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.edu.scnu.dao.ExperimentDao;
import main.java.edu.scnu.dao.PoccFileDao;
import main.java.edu.scnu.entity.Experiment;
import main.java.edu.scnu.entity.PoccFile;
import main.java.edu.scnu.service.ExperimentService;

@Service("experimentService")
@Transactional(propagation=Propagation.REQUIRED,
	isolation=Isolation.DEFAULT,timeout=5)
public class ExperimentServiceImpl implements ExperimentService{
	@Resource(name="experimentDao")
	private ExperimentDao experimentDao;
	@Resource(name="poccFileDao")
	private PoccFileDao poccFileDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Experiment> getAllExper() {
		return experimentDao.findAll(Experiment.class);
	}
	@Override
	@Transactional(readOnly=true)
	public List<PoccFile> getAllPoccFile(String user_acctID) {
		return poccFileDao.findByUserID(user_acctID);
	}	
	
	@Override
	@Transactional(readOnly=true)
	public Experiment getExperiment(int id) {
		return experimentDao.get(Experiment.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<PoccFile> getPoccFileByid(String user_acctID, int expID) {
		List<PoccFile> poccfileList = getAllPoccFile(user_acctID);
		List<PoccFile> list = new ArrayList<PoccFile>();
		for (PoccFile poccFile : poccfileList) {
			if(poccFile.getExperiment().getId()==expID){
				list.add(poccFile);
			}
		}
		return list;
	}
	
	@Override
	public boolean savePoccFile(PoccFile poccfile) {
		List<PoccFile> fileList= poccFileDao.findByUserID(poccfile.getUser_acctID());
		if(fileList.size()<5||fileList==null){//限制5个文件
			poccFileDao.update(poccfile);//saveOrUpdate
		}else{
			for (PoccFile poccFile2 : fileList) {
				String s[] = poccFile2.getFile_name().split("_");
				s[2] = (Integer.parseInt(s[2])-1)+"";
				poccFile2.setFile_name(StringUtils.join(s, "_"));
				poccFileDao.update(poccFile2);
			}
			PoccFile oldpoccfile = fileList.get(0);//删除最早文件
			//从磁盘中删除
			File f = new File(oldpoccfile.getFile_url());
			if(f.exists())
			f.delete();
			//删除数据库记录
			poccFileDao.delete(oldpoccfile);
			//保存新文件
			poccFileDao.update(poccfile);
		}
		return true;
	}
	
	@Override
	public boolean deletePoccFile(int id,String user_acctID) {
		PoccFile file = poccFileDao.get(PoccFile.class, id);
		if(file==null){
			return false;
		}else{
			//删除磁盘记录
			File f = new File(file.getFile_url());
			if(f.exists())
			f.delete();
			//删除数据库记录
			poccFileDao.delete(PoccFile.class, id);
			//重排文件名
			List<PoccFile> fileList= poccFileDao.findByUserID(user_acctID);
			for (int i = 0; i < fileList.size(); i++) {
				String s[] = fileList.get(i).getFile_name().split("_");
				s[2] = (i+1)+"";
				fileList.get(i).setFile_name(StringUtils.join(s, "_"));
				poccFileDao.update(fileList.get(i));
			}
		}
		return true;
	}
	
	@Override
	@Transactional(readOnly=true)
	public PoccFile getPoccFile(int id) {
		return poccFileDao.get(PoccFile.class, id);
	}
	
	//get,set
	public void setExperimentDao(ExperimentDao experimentDao) {
		this.experimentDao = experimentDao;
	}
	public void setPoccFileDao(PoccFileDao poccFileDao) {
		this.poccFileDao = poccFileDao;
	}

}
