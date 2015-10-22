package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.IPayRecordDao;
import com.xiaba2.bullfight.domain.PayRecord;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class PayRecordService extends BaseService<PayRecord, UUID> {
@Resource
private IPayRecordDao payRecordDao;
@Override
protected IBaseDao<PayRecord, UUID> getEntityDao() {
return payRecordDao;
}
}