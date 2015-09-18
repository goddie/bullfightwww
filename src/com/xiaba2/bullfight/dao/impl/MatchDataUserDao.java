package com.xiaba2.bullfight.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xiaba2.bullfight.dao.IMatchDataUserDao;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.core.AbstractHibernateDao;

@Repository
public class MatchDataUserDao extends AbstractHibernateDao<MatchDataUser, UUID>
		implements IMatchDataUserDao {

	@Override
 
	public List<Float> countUser(MatchDataUser matchDataUser) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("user", matchDataUser.getUser()));

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