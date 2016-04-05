package main.java.edu.scnu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.HistorySiteMsgDao;
import main.java.edu.scnu.entity.HistorySiteMsg;
import main.java.edu.scnu.entity.SiteMsg;

@Repository(value="historySiteMsgDao")
public class HistorySiteMsgDaoImpl extends BaseDaoImpl<HistorySiteMsg>
	implements HistorySiteMsgDao
{
	@Override
	public List<HistorySiteMsg> getHistoryMsgByReceiverID(int receiverID,int isread,int isdelete) {
		String hql = "select hsm from historysitemsg hsm where hsm.receiverID = ? and isread = ? and isdelete = ?";
		Object params[] ={receiverID,isread,isdelete};
		return find(hql, params);
	}

	@Override
	public List<HistorySiteMsg> getDeleteMsgByReceiverID(int receiverID,
			int isdelete) {
		String hql = "select hsm from historysitemsg hsm where hsm.receiverID = ? and isdelete = ?";
		Object params[] ={receiverID,isdelete};
		return find(hql, params);
	}

	@Override
	public HistorySiteMsg getHistoryMsgBySiteMsg(int receiverID, SiteMsg siteMsg) {
		String hql = "select hsm from historysitemsg hsm where hsm.receiverID = ? and siteMsg = ?";
		Object params[] ={receiverID,siteMsg};
		List<HistorySiteMsg> list = find(hql, params);
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<HistorySiteMsg> getReadMsgByReceiverID(int receiverID,
			int isread) {
		String hql = "select hsm from historysitemsg hsm where hsm.receiverID = ? and isread = ?";
		Object params[] ={receiverID,isread};
		return find(hql, params);
	}

}
