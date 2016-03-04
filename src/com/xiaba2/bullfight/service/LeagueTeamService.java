package com.xiaba2.bullfight.service;

import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.ILeagueTeamDao;
import com.xiaba2.bullfight.domain.League;
import com.xiaba2.bullfight.domain.LeagueTeam;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class LeagueTeamService extends BaseService<LeagueTeam, UUID> {
	@Resource
	private ILeagueTeamDao leagueTeamDao;

	@Override
	protected IBaseDao<LeagueTeam, UUID> getEntityDao() {
		return leagueTeamDao;
	}
	
	@Transactional
	public LeagueTeam getByTeamLeague(UUID leagueId,UUID teamId)
	{
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("league.id",leagueId));
		criteria.add(Restrictions.eq("team.id",teamId));
		criteria.add(Restrictions.eq("isDelete",0));
		
		List<LeagueTeam> list= findByCriteria(criteria);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		
		return null;
	}
	
	@Transactional
	public LeagueTeam getByTeamLeague(League league,Team team)
	{
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("league",league));
		criteria.add(Restrictions.eq("team",team));
		criteria.add(Restrictions.eq("isDelete",0));
		
		List<LeagueTeam> list= findByCriteria(criteria);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		
		return null;
	}
	
}