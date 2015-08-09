package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.ITradeRecordDao;
import com.xiaba2.invest.domain.TradeRecord;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class TradeRecordDao extends AbstractHibernateDao<TradeRecord, UUID> implements
ITradeRecordDao {
}