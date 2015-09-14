package com.xiaba2.bullfight.dao;
 
import java.util.List;
import java.util.UUID;

 





import com.xiaba2.bullfight.domain.MatchDataTeam;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.core.IBaseDao;

public interface IMatchDataTeamDao extends IBaseDao<MatchDataTeam, UUID> {
	
	
	 /**
	 * 调整队伍一场的成绩
	 * @param matchDataUser
	 * @param add
	 */
	void updateTeamByUser(MatchDataUser matchDataUser,int add);

 
	/**
	 * 根据一个球员数据变化，重新统计队伍的场均数据
	 * @param matchDataUser
	 * @return
	 */
	List<Float> countTeam(MatchDataUser matchDataUser);
}