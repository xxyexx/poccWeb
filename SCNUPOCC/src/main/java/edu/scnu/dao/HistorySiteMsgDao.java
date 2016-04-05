package main.java.edu.scnu.dao;

import java.util.List;

import main.java.edu.scnu.entity.HistorySiteMsg;
import main.java.edu.scnu.entity.SiteMsg;

public interface HistorySiteMsgDao extends BaseDao<HistorySiteMsg>{
	/**
	 * 根据接收者id查询该用户已读或已删消息列表
	 * @param receiverID user.id
	 * @param isread 0:未读，1:已读
	 * @param isdelete 0:未删除，1:已删除
	 * @return HistorySiteMsg list
	 */
	List<HistorySiteMsg> getHistoryMsgByReceiverID(int receiverID,int isread,int isdelete);
	/**
	 * 已删除
	 * @param receiverID
	 * @param isdelete
	 * @return
	 */
	List<HistorySiteMsg> getDeleteMsgByReceiverID(int receiverID,int isdelete);
	/**
	 * 已读
	 * @param receiverID
	 * @param isdelete
	 * @return
	 */
	List<HistorySiteMsg> getReadMsgByReceiverID(int receiverID,int isread);
	/**
	 * 根据消息获取用户已读消息
	 * @param receiverID user.id
	 * @param siteMsg HistorySiteMsg.siteMsg
	 * @return HistorySiteMsg
	 */
	HistorySiteMsg getHistoryMsgBySiteMsg(int receiverID,SiteMsg siteMsg);
}
