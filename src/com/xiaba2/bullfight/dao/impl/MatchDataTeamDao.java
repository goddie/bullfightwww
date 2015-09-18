package com.xiaba2.bullfight.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.core.AbstractHibernateDao;

@Repository
public class MatchDataTeamDao extends AbstractHibernateDao<MatchDataTeam, UUID>
		implements IMatchDataTeamDao {

	@Override
	public void updateTeamByUser(MatchDataUser matchDataUser, int add) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight",
				matchDataUser.getMatchFight()));
		criteria.add(Restrictions.eq("team", matchDataUser.getTeam()));
		// criteria.add(Restrictions.eq("user", matchDataUser.getUser()));

		MatchDataTeam entity = new MatchDataTeam();

		List<MatchDataTeam> list = findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			entity = list.get(0);
		}

		entity.setMatchFight(matchDataUser.getMatchFight());
		entity.setTeam(matchDataUser.getTeam());
		entity.setLastModifiedDate(new Date());

		entity.setShot(entity.getShot() + matchDataUser.getShot() * add);
		entity.setGoal(entity.getGoal() + matchDataUser.getGoal() * add);
		
		if(entity.getShot()>0)
		{
			entity.setGoalPercent(entity.getGoal()/entity.getShot());
		}
		

		entity.setThreeShot(entity.getThreeShot()
				+ matchDataUser.getThreeShot() * add);
		entity.setThreeGoal(entity.getThreeGoal()
				+ matchDataUser.getThreeGoal() * add);
		
		if(entity.getThreeShot()>0)
		{
			entity.setThreeGoalPercent(entity.getThreeGoal()/entity.getThreeShot());
		}
		
		
		entity.setFree(entity.getFree() + matchDataUser.getFree() * add);
		entity.setFreeGoal(entity.getFreeGoal() + matchDataUser.getFreeGoal()
				* add);
		
		if(entity.getFree()>0)
		{
			entity.setFreeGoalPercent(entity.getFreeGoal()/entity.getFree());
		}
		

		entity.setScoring(entity.getScoring() + matchDataUser.getScoring()
				* add);
		entity.setRebound(entity.getRebound() + matchDataUser.getRebound()
				* add);
		entity.setAssist(entity.getAssist() + matchDataUser.getAssist() * add);
		entity.setBlock(entity.getBlock() + matchDataUser.getBlock() * add);
		entity.setSteal(entity.getSteal() + matchDataUser.getSteal() * add);
		entity.setTurnover(entity.getTurnover() + matchDataUser.getTurnover()
				* add);
		entity.setFoul(entity.getFoul() + matchDataUser.getFoul() * add);

		// entity.setCreatedDate(new Date());

		saveOrUpdate(entity);

	}

	
	@Override
 
	public List<Float> countTeam(MatchDataUser matchDataUser) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("team", matchDataUser.getTeam()));

		ProjectionList projList = Projections.projectionList();

		projList.add(Projections.avg("scoring"));
		projList.add(Projections.avg("rebound"));
		projList.add(Projections.avg("assist"));
		projList.add(Projections.avg("block"));
		projList.add(Projections.avg("steal"));
		projList.add(Projections.avg("turnover"));
		projList.add(Projections.avg("foul"));
		projList.add(Projections.avg("goalPercent"));
		projList.add(Projections.avg("freeGoalPercent"));
		projList.add(Projections.avg("threeGoalPercent"));
		projList.add(Projections.count("id"));
		projList.add(Projections.sum("scoring"));
		criteria.setProjection(projList);

		Criteria criteriaExecute = criteria.getExecutableCriteria(getSession());
		
 
		Object[] objList = (Object[])  criteriaExecute.list().get(0);
		
		List<Float> result = new ArrayList<Float>();
		for (Object object : objList) {
			result.add(Float.parseFloat(String.valueOf(object)));
		}

		return result;
	}
	
	
 

}