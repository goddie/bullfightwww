package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.IMatchFightDao;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class MatchFightService extends BaseService<MatchFight, UUID> {
@Resource
private IMatchFightDao matchFightDao;
@Override
protected IBaseDao<MatchFight, UUID> getEntityDao() {
return matchFightDao;
}
}