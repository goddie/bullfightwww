package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.ILeagueDao;
import com.xiaba2.bullfight.domain.League;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class LeagueService extends BaseService<League, UUID> {
@Resource
private ILeagueDao leagueDao;
@Override
protected IBaseDao<League, UUID> getEntityDao() {
return leagueDao;
}
}