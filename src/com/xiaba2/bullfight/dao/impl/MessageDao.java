package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IMessageDao;
import com.xiaba2.bullfight.domain.Message;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class MessageDao extends AbstractHibernateDao<Message, UUID> implements
IMessageDao {
}