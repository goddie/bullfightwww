package com.xiaba2.bullfight.dao.impl;

import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IMatchFightDao;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.core.AbstractHibernateDao;
import com.xiaba2.util.HttpUtil;

@Repository
public class MatchFightDao extends AbstractHibernateDao<MatchFight, UUID> implements IMatchFightDao {
	
	
	
	/**
	 *  比赛比分
	 * @param entity
	 */
	public void updateScore(MatchFight matchFight)
	{
		MatchFight entity = get(matchFight.getId());
		
		//主队
		String sql1 = "select sum(scoring) scoring from db_bullfight_matchdatateam "
				+ "where isdelete=0 and team_id=unhex('"+entity.getHost().getId().toString().replaceAll("-", "")+"') "
						+ "and matchFight_id = unhex('"+entity.getId().toString().replaceAll("-", "")+"') ";
		Map<String,Object> map1 = findByNativeSQL(sql1).get(0);
		float scoring1 = HttpUtil.toFloat(map1.get("scoring"));
		
		//客队得分
		String sql2 = "select sum(scoring) scoring from db_bullfight_matchdatateam "
				+ "where isdelete=0 and team_id=unhex('"+entity.getGuest().getId().toString().replaceAll("-", "")+"') "
				+ "and matchFight_id = unhex('"+entity.getId().toString().replaceAll("-", "")+"') ";
		Map<String,Object> map2 = findByNativeSQL(sql2).get(0);
		float scoring2 = HttpUtil.toFloat(map2.get("scoring"));
		
		entity.setHostScore(scoring1);
		entity.setGuestScore(scoring2);
		
		if(scoring1>scoring2)
		{
			entity.setWinner(entity.getHost());
			entity.setLoser(entity.getGuest());
		}
		
		
		if(scoring1<scoring2)
		{
			entity.setWinner(entity.getGuest());
			entity.setLoser(entity.getHost());
		}

		
		saveOrUpdate(entity);
		
	}
	
}