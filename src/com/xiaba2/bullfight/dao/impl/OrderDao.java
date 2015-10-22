package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IOrderDao;
import com.xiaba2.bullfight.domain.Order;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class OrderDao extends AbstractHibernateDao<Order, UUID> implements
IOrderDao {
}