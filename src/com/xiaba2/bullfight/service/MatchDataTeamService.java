package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.IMatchDataTeamDao;
import com.xiaba2.bullfight.domain.MatchDataTeam;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class MatchDataTeamService extends BaseService<MatchDataTeam, UUID> {
@Resource
private IMatchDataTeamDao matchDataTeamDao;
@Override
protected IBaseDao<MatchDataTeam, UUID> getEntityDao() {
return matchDataTeamDao;
}
}