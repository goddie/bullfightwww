package com.xiaba2.bullfight.service;

import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.IMessageDao;
import com.xiaba2.bullfight.domain.Message;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
import com.xiaba2.core.JPushUtil;

@Service
public class MessageService extends BaseService<Message, UUID> {
	@Resource
	private IMessageDao messageDao;

	@Override
	protected IBaseDao<Message, UUID> getEntityDao() {
		return messageDao;
	}
	
	
	/**
	 * 推送消息
	 * 消息类型 
	 * 1 通知队长
	 * 2 邀请入会
	 * 3 新闻回复
	 * 4 约战回复
	 * 5 约战评论
	 * @param entity
	 */
	public void pushMessage(Message entity)
	{
		if (entity.getType()==1) {
			
		}
		
		if (entity.getType()==2) {
			
		}
		
		if (entity.getType()==3) {
			
			String alias = entity.getSendTo().getId().toString();
			String content = entity.getContent();
			
			JPushUtil.push(alias, content);
			//JPushUtil.push(content);
		}
		
		if (entity.getType()==4) {
			
		}
		
		if (entity.getType()==5) {
			
		}
	}
}