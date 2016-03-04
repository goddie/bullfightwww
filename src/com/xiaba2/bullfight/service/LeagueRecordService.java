package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.ILeagueRecordDao;
import com.xiaba2.bullfight.domain.LeagueRecord;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class LeagueRecordService extends BaseService<LeagueRecord, UUID> {
@Resource
private ILeagueRecordDao leagueRecordDao;
@Override
protected IBaseDao<LeagueRecord, UUID> getEntityDao() {
return leagueRecordDao;
}
}