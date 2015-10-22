package com.xiaba2.bullfight.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xiaba2.bullfight.dao.ITeamUserDao;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.domain.TeamUser;
import com.xiaba2.cms.domain.User;
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
	
	
	
	
	@Transactional
	public Team getTeamByUser(User user)
	{
		DetachedCriteria criteria = teamUserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		List<TeamUser> list = teamUserDao.findByCriteria(criteria);
		if(!list.isEmpty())
		{
			return list.get(0).getTeam();
		}
		
		return null;
	}
	
	@Transactional
	public List<Team> getTeamsByUser(User user)
	{
		DetachedCriteria criteria = teamUserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		List<TeamUser> list = teamUserDao.findByCriteria(criteria);
		
		List<Team> list2= new ArrayList<Team>();
		
		if(list!=null)
		{
			for (TeamUser tu : list) {
				
				list2.add(tu.getTeam());
				
			}
			
			return list2;
		}
		
		return null;
	}
	
	@Transactional
	public TeamUser getTeamUserByUser(User user)
	{
		DetachedCriteria criteria = teamUserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		List<TeamUser> list = teamUserDao.findByCriteria(criteria);
		if(!list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}
	
	/**
	 * 用户是否在队伍中
	 * @param user
	 * @param team
	 * @return
	 */
	@Transactional
	public Boolean isUserInTeam(User user ,Team team)
	{
		
		DetachedCriteria criteria = teamUserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("team", team));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		List<TeamUser> list = teamUserDao.findByCriteria(criteria);
		if(list==null||list.size()==0)
		{
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 队伍成员
	 * @param team
	 * @return
	 */
	@Transactional
	public List<User> getUsersByTeam(Team team)
	{
		
		DetachedCriteria criteria = teamUserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("team", team));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		List<TeamUser> list = teamUserDao.findByCriteria(criteria);
		
		List<User> users= new ArrayList<User>();
		
		if(list==null||list.size()==0)
		{
			return null;
		}
		
		for (TeamUser teamUser : list) {
			users.add(teamUser.getUser());
		}
		
		return users;
	}
	
}