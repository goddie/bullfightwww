package com.xiaba2.bullfight.service;

import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.ITeamDao;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class TeamService extends BaseService<Team, UUID> {
	@Resource
	private ITeamDao teamDao;

	@Override
	protected IBaseDao<Team, UUID> getEntityDao() {
		return teamDao;
	}
	
	
}