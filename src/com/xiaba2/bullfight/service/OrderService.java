package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.IOrderDao;
import com.xiaba2.bullfight.domain.Order;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class OrderService extends BaseService<Order, UUID> {
@Resource
private IOrderDao orderDao;
@Override
protected IBaseDao<Order, UUID> getEntityDao() {
return orderDao;
}
}