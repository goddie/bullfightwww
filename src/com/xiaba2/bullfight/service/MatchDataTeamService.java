package com.xiaba2.bullfight.service;

 
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

 








import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xiaba2.bullfight.dao.IMatchDataTeamDao;
import com.xiaba2.bullfight.domain.MatchDataTeam;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class MatchDataTeamService extends BaseService<MatchDataTeam, UUID> {
	@Resource
	private IMatchDataTeamDao matchDataTeamDao;
	
	@Resource
	private TeamService teamService;

	@Override
	protected IBaseDao<MatchDataTeam, UUID> getEntityDao() {
		return matchDataTeamDao;
	}

//	/**
//	 * 调整队伍的成绩
//	 * 
//	 * @param matchFight
//	 * @param user
//	 */
//	@Transactional
//	public void updateTeamByUser(MatchDataUser matchDataUser,int add)
//	{
//		matchDataTeamDao.updateTeamByUser(matchDataUser, add);
//	}
//	
	/**
	 * 根据队伍成绩统计队伍单场、整体成绩
	 * @param matchFight
	 * @param team
	 */
	@Transactional
	public void updateMatchTeam(MatchFight matchFight,Team team)
	{
		matchDataTeamDao.updateMatchTeam(matchFight, team);
	}
	
	
	
//	@Transactional
//	public void countTeam(MatchDataUser matchDataUser)
//	{
// 
//		List<Float> rs =  teamService.updateData(team);  matchDataTeamDao.countTeam(matchDataUser);
// 
//		
//		Team t = teamService.get(matchDataUser.getTeam().getId()) ;
//		
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
//		teamService.save(t);
//	}
	
	
	/**
	 * 获取一场比赛的成绩
	 * @param matchFight
	 * @param team
	 * @return
	 */
	@Transactional
	public MatchDataTeam getByMatchFight(MatchFight matchFight,Team team)
	{
		DetachedCriteria criteria = matchDataTeamDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		criteria.add(Restrictions.eq("team", team));
		
		List<MatchDataTeam> list = matchDataTeamDao.findByCriteria(criteria);
		
		return list.isEmpty()?null:list.get(0);
	}
 
	
	/**
	 * 删除对战
	 * @param matchFight
	 */
	@Transactional
	public void deleteByMatchFight(MatchFight matchFight)
	{
		DetachedCriteria criteria = matchDataTeamDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		
		List<MatchDataTeam> list = matchDataTeamDao.findByCriteria(criteria);
		
		for (MatchDataTeam matchDataTeam : list) {
			matchDataTeam.setIsDelete(1);
			saveOrUpdate(matchDataTeam);

		}
		
		updateMatchTeam(matchFight, matchFight.getHost());
		updateMatchTeam(matchFight, matchFight.getGuest());
		
	}
	
}