package com.xiaba2.invest.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.invest.dao.ITradeRecordDao;
import com.xiaba2.invest.domain.TradeRecord;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class TradeRecordService extends BaseService<TradeRecord, UUID> {
@Resource
private ITradeRecordDao tradeRecordDao;
@Override
protected IBaseDao<TradeRecord, UUID> getEntityDao() {
return tradeRecordDao;
}
}