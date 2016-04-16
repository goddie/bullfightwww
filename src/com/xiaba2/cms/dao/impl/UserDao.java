package com.xiaba2.cms.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.cms.dao.IUserDao;
import com.xiaba2.cms.domain.User;
import com.xiaba2.core.AbstractHibernateDao;
import com.xiaba2.util.HttpUtil;

@Repository
public class UserDao extends AbstractHibernateDao<User, UUID> implements IUserDao {

	@Override
	public void updateData(User user) {
		
		User entity =  get(user.getId());
		
		// TODO Auto-generated method stub
		String sql = "select avg(goalPercent) goalPercent,avg(freeGoalPercent) freeGoalPercent,avg(threeGoalPercent) threeGoalPercent,"
				+ "avg(shot) shot,avg(goal) goal,avg(threeShot) threeShot,avg(threeGoal) threeGoal,avg(free) free,avg(freeGoal) freeGoal,"
				+ "sum(scoring) scoring,avg(rebound) rebound,avg(assist) assist,avg(block) block,avg(steal) steal,avg(turnover) turnover,avg(foul) foul,count(id) played"
				+ " from db_bullfight_matchdatauser "
				+ "where isdelete=0 and user_id = unhex('"+entity.getId().toString().replaceAll("-", "")+"')";

		List<Map<String, Object>> listMap = findByNativeSQL(sql);
		
		Map<String,Object> map = listMap.get(0);
			
//		entity.setShot(Float.parseFloat(map.get("shot").toString()));
//		entity.setGoal(Float.parseFloat(map.get("goal").toString()));
//		if(entity.getShot()>0)
//		{
//			entity.setGoalPercent(entity.getGoal()/entity.getShot());
//		}
//		
//		entity.setThreeShot(Float.parseFloat(map.get("threeShot").toString()));
//		entity.setThreeGoal(Float.parseFloat(map.get("threeGoal").toString()));
//		if(entity.getThreeShot()>0)
//		{
//			entity.setThreeGoalPercent(entity.getThreeGoal()/entity.getThreeShot());
//		}
//		
//		entity.setFree(Float.parseFloat(map.get("free").toString()));
//		entity.setFreeGoal(Float.parseFloat(map.get("freeGoal").toString()));
//		if(entity.getFree()>0)
//		{
//			entity.setFreeGoalPercent(entity.getFreeGoal()/entity.getFree());
//		}
		

		entity.setGoalPercent(HttpUtil.toFloat(map.get("goalPercent")));
		entity.setFreeGoalPercent(HttpUtil.toFloat(map.get("freeGoalPercent")));
		entity.setThreeGoalPercent(HttpUtil.toFloat(map.get("threeGoalPercent")));
		
		entity.setScoring(HttpUtil.toFloat(map.get("scoring")));
		entity.setRebound(HttpUtil.toFloat(map.get("rebound")));
		entity.setAssist(HttpUtil.toFloat(map.get("assist")));
		entity.setBlock(HttpUtil.toFloat(map.get("block")));
		entity.setSteal(HttpUtil.toFloat(map.get("steal")));
		entity.setTurnover(HttpUtil.toFloat(map.get("turnover")));
		entity.setFoul(HttpUtil.toFloat(map.get("foul")));
		entity.setPlayCount(HttpUtil.toFloat(map.get("played")));
		
 
		
		saveOrUpdate(entity);
	}
	
	
}