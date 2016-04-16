package com.xiaba2.bullfight.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xiaba2.bullfight.dao.IMatchDataTeamDao;
import com.xiaba2.bullfight.dao.IMatchDataUserDao;
import com.xiaba2.bullfight.dao.ITeamDao;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class MatchDataUserService extends BaseService<MatchDataUser, UUID> {
	@Resource
	private IMatchDataUserDao matchDataUserDao;

	@Override
	protected IBaseDao<MatchDataUser, UUID> getEntityDao() {
		return matchDataUserDao;
	}
	
	@Resource
	MatchDataTeamService matchDataTeamService; 
	
	@Resource
	UserService userService;
 
	@Resource
	TeamService  teamService;

	/**
	 * 删除重复数据
	 * @param entity
	 */
	@Transactional
	public void deleteAndUpdate(MatchDataUser entity)
	{
		delete(entity);
		matchDataTeamService.updateMatchTeam(entity.getMatchFight(),entity.getTeam());
		teamService.updateData(entity.getTeam());
		
	}
	
//	/**
//	 * 保存队员比赛数据，同时更新队伍，队员个人数据
//	 * @param entity
//	 */
//	@Transactional
//	public void saveUserData(MatchDataUser entity) {
//		matchDataUserDao.save(entity);
//		userService.updateData(entity.getUser());
//		
//		matchDataTeamService.updateMatchTeam(entity.getMatchFight(),entity.getTeam());
//		teamService.updateData(entity.getTeam());
//		
////		countUser(entity);
//		
//	}
//	
	
	
//	@Transactional
//	void countUser(MatchDataUser matchDataUser)
//	{
// 
//		List<Float> rs = matchDataUserDao.countUser(matchDataUser);
//		User t = userService.get(matchDataUser.getUser().getId());
//		t.setScoringAvg(rs.get(0));
//		t.setRebound(rs.get(1));
//		t.setAssist(rs.get(2));
//		t.setBlock(rs.get(3));
//		t.setSteal(rs.get(4));
//		t.setTurnover(rs.get(5));
//		t.setFoul(rs.get(6));
//		t.setGoalPercent(rs.get(7));
//		t.setFreeGoalPercent(rs.get(8));
//		t.setThreeGoalPercent(rs.get(9));
//		t.setPlayCount(rs.get(10));
//		t.setScoring(rs.get(11));
//		userService.saveOrUpdate(t);
//	}
 

}