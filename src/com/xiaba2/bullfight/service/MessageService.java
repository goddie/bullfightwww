package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.IMessageDao;
import com.xiaba2.bullfight.domain.Message;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class MessageService extends BaseService<Message, UUID> {
@Resource
private IMessageDao messageDao;
@Override
protected IBaseDao<Message, UUID> getEntityDao() {
return messageDao;
}
}