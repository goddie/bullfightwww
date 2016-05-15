package com.xiaba2.bullfight.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xiaba2.bullfight.dao.IMatchDataTeamDao;
import com.xiaba2.bullfight.dao.ITeamDao;
import com.xiaba2.bullfight.domain.MatchDataTeam;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.core.AbstractHibernateDao;
import com.xiaba2.util.HttpUtil;

@Repository
public class MatchDataTeamDao extends AbstractHibernateDao<MatchDataTeam, UUID>
		implements IMatchDataTeamDao {

//	@Override
//	public void updateTeamByUser(MatchDataUser matchDataUser, int add) {
//		DetachedCriteria criteria = createDetachedCriteria();
//		criteria.add(Restrictions.eq("isDelete", 0));
//		criteria.add(Restrictions.eq("matchFight",matchDataUser.getMatchFight()));
//		criteria.add(Restrictions.eq("team", matchDataUser.getTeam()));
//		// criteria.add(Restrictions.eq("user", matchDataUser.getUser()));
//
//		MatchDataTeam entity = null;
//
//		List<MatchDataTeam> list = findByCriteria(criteria);
//		if (list != null && list.size() > 0) {
//			entity = list.get(0);
//		}else
//		{
//			entity = new MatchDataTeam();
//			entity.setCreatedDate(new Date());
//		}
//	
//
//		entity.setShot(entity.getShot() + matchDataUser.getShot() * add);
//		entity.setGoal(entity.getGoal() + matchDataUser.getGoal() * add);
//		
//		if(entity.getShot()>0)
//		{
//			entity.setGoalPercent(entity.getGoal()/entity.getShot());
//		}
//		
//
//		entity.setThreeShot(entity.getThreeShot()
//				+ matchDataUser.getThreeShot() * add);
//		entity.setThreeGoal(entity.getThreeGoal()
//				+ matchDataUser.getThreeGoal() * add);
//		
//		if(entity.getThreeShot()>0)
//		{
//			entity.setThreeGoalPercent(entity.getThreeGoal()/entity.getThreeShot());
//		}
//		
//		
//		entity.setFree(entity.getFree() + matchDataUser.getFree() * add);
//		entity.setFreeGoal(entity.getFreeGoal() + matchDataUser.getFreeGoal()
//				* add);
//		
//		if(entity.getFree()>0)
//		{
//			entity.setFreeGoalPercent(entity.getFreeGoal()/entity.getFree());
//		}
//		
//
//		entity.setScoring(entity.getScoring() + matchDataUser.getScoring()
//				* add);
//		entity.setRebound(entity.getRebound() + matchDataUser.getRebound()
//				* add);
//		entity.setAssist(entity.getAssist() + matchDataUser.getAssist() * add);
//		entity.setBlock(entity.getBlock() + matchDataUser.getBlock() * add);
//		entity.setSteal(entity.getSteal() + matchDataUser.getSteal() * add);
//		entity.setTurnover(entity.getTurnover() + matchDataUser.getTurnover()
//				* add);
//		entity.setFoul(entity.getFoul() + matchDataUser.getFoul() * add);
//
//		// entity.setCreatedDate(new Date());
//
//		saveOrUpdate(entity);
//
//	}
	
	
	
	
	/**
	 * 根据成绩统计队伍单场、整体成绩
	 * @param matchFight
	 * @param team
	 */
	@Override
	public void updateMatchTeam(MatchFight matchFight,Team team) {
		
		if(matchFight.getStatus()!=2)
		{
			return;
		}
		
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight",matchFight));
		criteria.add(Restrictions.eq("team", team));
		// criteria.add(Restrictions.eq("user", matchDataUser.getUser()));

		MatchDataTeam entity = null;

		List<MatchDataTeam> list = findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			entity = list.get(0);
		}else
		{
			entity = new MatchDataTeam();
			entity.setCreatedDate(new Date());
		}
	
		String sql = "select sum(shot) shot,sum(goal) goal,sum(threeShot) threeShot,sum(threeGoal) threeGoal,sum(free) free,sum(freeGoal) freeGoal,"
				+ "sum(scoring) scoring,sum(rebound) rebound,sum(assist) assist,sum(block) block,sum(steal) steal,sum(turnover) turnover,sum(foul) foul"
				+ " from db_bullfight_matchdatauser "
				+ "where isdelete=0 and team_id = unhex('"+team.getId().toString().replaceAll("-", "")+"')"
						+ " and matchfight_id = unhex('"+matchFight.getId().toString().replaceAll("-", "")+"')";

		List<Map<String, Object>> listMap = findByNativeSQL(sql);
		
		Map<String,Object> map = listMap.get(0);
		
		entity.setShot(HttpUtil.toFloat(map.get("shot")));
		entity.setGoal(HttpUtil.toFloat(map.get("goal")));
		if(entity.getShot()>0)
		{
			entity.setGoalPercent(entity.getGoal()/entity.getShot());
		}
		
		entity.setThreeShot(HttpUtil.toFloat(map.get("threeShot")));
		entity.setThreeGoal(HttpUtil.toFloat(map.get("threeGoal")));
		if(entity.getThreeShot()>0)
		{
			entity.setThreeGoalPercent(entity.getThreeGoal()/entity.getThreeShot());
		}
		
		entity.setFree(HttpUtil.toFloat(map.get("free")));
		entity.setFreeGoal(HttpUtil.toFloat(map.get("freeGoal")));
		if(entity.getFree()>0)
		{
			entity.setFreeGoalPercent(entity.getFreeGoal()/entity.getFree());
		}
		
		
		entity.setScoring(HttpUtil.toFloat(map.get("scoring")));
		entity.setRebound(HttpUtil.toFloat(map.get("rebound")));
		entity.setAssist(HttpUtil.toFloat(map.get("assist")));
		entity.setBlock(HttpUtil.toFloat(map.get("block")));
		entity.setSteal(HttpUtil.toFloat(map.get("steal")));
		entity.setTurnover(HttpUtil.toFloat(map.get("turnover")));
		entity.setFoul(HttpUtil.toFloat(map.get("foul")));
		
		entity.setMatchFight(matchFight);
		entity.setTeam(team);
		entity.setLastModifiedDate(new Date());
		saveOrUpdate(entity);

	}

	
//	@Override
// 
//	public List<Float> countTeam(MatchDataUser matchDataUser) {
//		DetachedCriteria criteria = createDetachedCriteria();
//		criteria.add(Restrictions.eq("isDelete", 0));
//		criteria.add(Restrictions.eq("team", matchDataUser.getTeam()));
//
//		ProjectionList projList = Projections.projectionList();
//
//		projList.add(Projections.avg("scoring"));
//		projList.add(Projections.avg("rebound"));
//		projList.add(Projections.avg("assist"));
//		projList.add(Projections.avg("block"));
//		projList.add(Projections.avg("steal"));
//		projList.add(Projections.avg("turnover"));
//		projList.add(Projections.avg("foul"));
//		projList.add(Projections.avg("goalPercent"));
//		projList.add(Projections.avg("freeGoalPercent"));
//		projList.add(Projections.avg("threeGoalPercent"));
//		projList.add(Projections.count("id"));
//		projList.add(Projections.sum("scoring"));
//		criteria.setProjection(projList);
//
//		Criteria criteriaExecute = criteria.getExecutableCriteria(getSession());
//		
// 
//		Object[] objList = (Object[])  criteriaExecute.list().get(0);
//		
//		List<Float> result = new ArrayList<Float>();
//		for (Object object : objList) {
//			result.add(Float.parseFloat(String.valueOf(object)));
//		}
//
//		return result;
//	}




	
	
	
 

}