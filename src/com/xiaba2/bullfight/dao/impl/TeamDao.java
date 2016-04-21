package com.xiaba2.bullfight.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.ITeamDao;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.core.AbstractHibernateDao;
import com.xiaba2.util.HttpUtil;

@Repository
public class TeamDao extends AbstractHibernateDao<Team, UUID> implements
		ITeamDao {

	@Override
	public void updateData(Team team) {
	
		Team entity = get(team.getId());
		
		String sql = "select avg(shot) shot,avg(goal) goal,avg(threeShot) threeShot,avg(threeGoal) threeGoal,avg(free) free,avg(freeGoal) freeGoal,"
				+ "avg(scoring) scoring,avg(rebound) rebound,avg(assist) assist,avg(block) block,avg(steal) steal,avg(turnover) turnover,avg(foul) foul"
				+ " from db_bullfight_matchdatateam "
				+ "where isdelete=0 and team_id = unhex('"+entity.getId().toString().replaceAll("-", "")+"')";

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
		
		float goal = HttpUtil.toFloat(map.get("goal"));
		float shot = HttpUtil.toFloat(map.get("shot"));
 
		if(shot>0)
		{
			entity.setGoalPercent(goal/shot);
		}
		
		
		float threeShot = HttpUtil.toFloat(map.get("threeShot"));
		float threeGoal = HttpUtil.toFloat(map.get("threeGoal"));
		if(threeShot>0)
		{
			entity.setThreeGoalPercent(threeGoal/threeShot);
		}
		
		float free = HttpUtil.toFloat(map.get("free"));
		float freeGoal= HttpUtil.toFloat(map.get("freeGoal"));
		if(free>0)
		{
			entity.setFreeGoalPercent(freeGoal/free);
		}
		
		entity.setScoring(HttpUtil.toFloat(map.get("scoring")));
		entity.setRebound(HttpUtil.toFloat(map.get("rebound")));
		entity.setAssist(HttpUtil.toFloat(map.get("assist")));
		entity.setBlock(HttpUtil.toFloat(map.get("block")));
		entity.setSteal(HttpUtil.toFloat(map.get("steal")));
		entity.setTurnover(HttpUtil.toFloat(map.get("turnover")));
		entity.setFoul(HttpUtil.toFloat(map.get("foul")));
		
		
		saveOrUpdate(entity);
		
	}
	
	
	/**
	 * 统计胜负
	 * @param team
	 */
	public void countWinLose(Team entity)
	{
		
		
		Team team = get(entity.getId());
		
		String teamid = team.getId().toString().replaceAll("-", "");
		
		//比赛场次
		String sql1 = "select count(id) played from db_bullfight_matchfight"
				+ " where isdelete=0 and status=2 and ( host_id = unhex('"+teamid+"') "
			    + " or guest_id = unhex('"+teamid+"') )";
		
		Map<String,Object> map1 = findByNativeSQL(sql1).get(0);
		team.setPlayCount(HttpUtil.toFloat(map1.get("played")));
		
		
		String sql2 = "select count(id) win from db_bullfight_matchfight"
				+ " where isdelete=0 and status=2 and hostScore>guestScore and host_id = unhex('"+teamid+"')";
		Map<String,Object> map2 = findByNativeSQL(sql2).get(0);
		float win1 = HttpUtil.toFloat(map2.get("win"));
		
		String sql3 = "select count(id) win from db_bullfight_matchfight"
				+ " where isdelete=0 and status=2 and hostScore<guestScore and guest_id = unhex('"+teamid+"')";
		Map<String,Object> map3= findByNativeSQL(sql3).get(0);
		float win2 = HttpUtil.toFloat(map3.get("win"));
		
		
		team.setWin(win1+win2);
		team.setLose(team.getPlayCount()-team.getWin());
		
		saveOrUpdate(team);
	}
	
}