package com.xiaba2.bullfight.dao;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.cms.domain.User;
import com.xiaba2.core.IBaseDao;
public interface IMatchDataUserDao extends IBaseDao<MatchDataUser, UUID> {
	
	/**
	 * 根据一场记录，重新统计球员个人综合数据
	 * @param matchDataUser
	 * @return
	 */
//	List<Float> countUser(MatchDataUser matchDataUser);
 
	
	/**
	 * 
	 * @param matchFight
	 * @param team
	 * @param user
	 */
	void  updateMatchUser(MatchFight matchFight,Team team,User user);
}