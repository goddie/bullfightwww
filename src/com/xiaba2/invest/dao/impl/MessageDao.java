package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IMessageDao;
import com.xiaba2.invest.domain.Message;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class MessageDao extends AbstractHibernateDao<Message, UUID> implements
IMessageDao {
}