package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IPayRecordDao;
import com.xiaba2.bullfight.domain.PayRecord;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class PayRecordDao extends AbstractHibernateDao<PayRecord, UUID> implements
IPayRecordDao {
}