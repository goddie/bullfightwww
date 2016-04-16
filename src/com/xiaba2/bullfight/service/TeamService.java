package com.xiaba2.bullfight.service;

import java.util.UUID;
import javax.annotation.Resource;
import javax.transaction.Transactional;

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
	
	
	/**
	 * 升级队伍数据
	 * @param team
	 */
	@Transactional
	public void updateData(Team team)
	{
		teamDao.updateData(team);
	}
	
	/**
	 * 统计输赢、比赛场次
	 * @param team
	 */
	@Transactional
	public void countWinLose(Team team)
	{
		teamDao.countWinLose(team);
	}
}