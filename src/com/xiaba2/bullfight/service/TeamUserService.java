package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.ITeamUserDao;
import com.xiaba2.bullfight.domain.TeamUser;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class TeamUserService extends BaseService<TeamUser, UUID> {
@Resource
private ITeamUserDao teamUserDao;
@Override
protected IBaseDao<TeamUser, UUID> getEntityDao() {
return teamUserDao;
}
}