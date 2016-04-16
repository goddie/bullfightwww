package com.xiaba2.bullfight.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xiaba2.bullfight.dao.IMatchDataUserDao;
import com.xiaba2.bullfight.domain.MatchDataTeam;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.cms.domain.User;
import com.xiaba2.core.AbstractHibernateDao;

@Repository
public class MatchDataUserDao extends AbstractHibernateDao<MatchDataUser, UUID>
		implements IMatchDataUserDao {
	
	/**
	 * 根据个人成绩统计队伍单场、整体成绩
	 * @param matchFight
	 * @param team
	 */
	@Override
	public void updateMatchUser(MatchFight matchFight,Team team,User user) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight",matchFight));
		criteria.add(Restrictions.eq("user", user));
		// criteria.add(Restrictions.eq("user", matchDataUser.getUser()));

		MatchDataUser entity = null;

		List<MatchDataUser> list = findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			entity = list.get(0);
		}else
		{
			entity = new MatchDataUser();
			entity.setCreatedDate(new Date());
		}
	
		String sql = "select sum(shot) shot,sum(goal) goal,sum(threeShot) threeShot,sum(threeGoal) threeGoal,sum(free) free,sum(freeGoal) freeGoal"
				+ "sum(scoring) scoring,sum(rebound) rebound,sum(assist) assist,sum(block) block,sum(steal) steal,sum(turnover) turnover,sum(foul) foul"
				+ " from db_bullfight_matchdatauser "
				+ "where isdelete=0 and user_id = unhex('"+user.getId().toString().replaceAll("-", "")+"')"
						+ "and matchfight_id = unhex('"+matchFight.getId().toString().replaceAll("-", "")+"')";

		List<Map<String, Object>> listMap = findByNativeSQL(sql);
		
		Map<String,Object> map = listMap.get(0);
		
		entity.setShot(Float.parseFloat(map.get("shot").toString()));
		entity.setGoal(Float.parseFloat(map.get("goal").toString()));
		
		if(entity.getShot()>0)
		{
			entity.setGoalPercent(entity.getGoal()/entity.getShot());
		}
		
		entity.setThreeShot(Float.parseFloat(map.get("threeShot").toString()));
		entity.setThreeGoal(Float.parseFloat(map.get("threeGoal").toString()));
		if(entity.getThreeShot()>0)
		{
			entity.setThreeGoalPercent(entity.getThreeGoal()/entity.getThreeShot());
		}
		
		entity.setFree(Float.parseFloat(map.get("free").toString()));
		entity.setFreeGoal(Float.parseFloat(map.get("freeGoal").toString()));
		if(entity.getFree()>0)
		{
			entity.setFreeGoalPercent(entity.getFreeGoal()/entity.getFree());
		}
		
		
		entity.setScoring(Float.parseFloat(map.get("scoring").toString()));
		entity.setRebound(Float.parseFloat(map.get("rebound").toString()));
		entity.setAssist(Float.parseFloat(map.get("assist").toString()));
		entity.setBlock(Float.parseFloat(map.get("block").toString()));
		entity.setSteal(Float.parseFloat(map.get("steal").toString()));
		entity.setTurnover(Float.parseFloat(map.get("turnover").toString()));
		entity.setFoul(Float.parseFloat(map.get("foul").toString()));
		
		entity.setMatchFight(matchFight);
		entity.setUser(user);
		entity.setTeam(team);
		entity.setLastModifiedDate(new Date());
		saveOrUpdate(entity);

	}

//	@Override
//	public List<Float> countUser(MatchDataUser matchDataUser) {
//		DetachedCriteria criteria = createDetachedCriteria();
//		criteria.add(Restrictions.eq("isDelete", 0));
//		criteria.add(Restrictions.eq("user", matchDataUser.getUser()));
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
//		Object[] objList = (Object[])  criteriaExecute.list().get(0);
//		
//		List<Float> result = new ArrayList<Float>();
//		
//		for (Object object : objList) {
//			result.add(Float.parseFloat(String.valueOf(object)));
//		}
//
//		return result;
//	}

	
	
	
}