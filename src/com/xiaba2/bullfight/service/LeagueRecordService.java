package com.xiaba2.bullfight.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.ILeagueRecordDao;
import com.xiaba2.bullfight.domain.League;
import com.xiaba2.bullfight.domain.LeagueRecord;
import com.xiaba2.bullfight.domain.LeagueTeam;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
import com.xiaba2.util.HttpUtil;

@Service
public class LeagueRecordService extends BaseService<LeagueRecord, UUID> {
	@Resource
	private ILeagueRecordDao leagueRecordDao;

	@Override
	protected IBaseDao<LeagueRecord, UUID> getEntityDao() {
		return leagueRecordDao;
	}
	
	@Resource
	private LeagueTeamService leagueTeamService;
	
	/**
	 * 计算联赛积分
	 * @param matchFight
	 */
	@Transactional
	public void countRecord(MatchFight matchFight)
	{
		LeagueRecord host = findEntity(matchFight.getHost(),matchFight.getLeague());
		LeagueRecord guest = findEntity(matchFight.getGuest(),matchFight.getLeague());
		
		
		           
		//胜
		if(matchFight.getHostScore()>matchFight.getGuestScore())
		{
			host.setWin(host.getWin()+1);
			host.setScore(host.getScore()+3);
			host.setPointSum(host.getPointSum()+ (int)matchFight.getHostScore());
			host.setPlays(host.getPlays()+1);
			host.setPointAvg(host.getPointSum()/host.getPlays());
			
			
			guest.setLose(guest.getLose()+1);
			guest.setScore(guest.getScore()+1);
			guest.setPointSum(guest.getPointSum()+ (int)matchFight.getGuestScore());
			guest.setPlays(guest.getPlays()+1);
			guest.setPointAvg(guest.getPointSum()/guest.getPlays());
		}
		
		//负
		if(matchFight.getHostScore()<matchFight.getGuestScore())
		{
			
			host.setLose(host.getLose()+1);
			host.setScore(host.getScore()+1);
			host.setPointSum(host.getPointSum()+ (int)matchFight.getHostScore());
			host.setPlays(host.getPlays()+1);
			host.setPointAvg(host.getPointSum()/host.getPlays());
			
			
			guest.setWin(guest.getWin()+1);
			guest.setScore(guest.getScore()+3);
			guest.setPointSum(guest.getPointSum()+ (int)matchFight.getGuestScore());
			guest.setPlays(guest.getPlays()+1);
			guest.setPointAvg(guest.getPointSum()/guest.getPlays());
		}

		
		
		saveOrUpdate(host);
		saveOrUpdate(guest);
	}
	
	
	/**
	 * 计算整个联赛各队伍的积分
	 * @param league
	 */
	@Transactional
	public void countLeagueRecord(League league)
	{
		
		String leagueId = league.getId().toString().replaceAll("-", "");
		
		List<Team> list = leagueTeamService.getTeams(league);
		
		for (Team team : list) {
			
			String teamId = team.getId().toString().replaceAll("-", "");
			
			String win1 ="select count(id) win1,sum(hostScore) sum1 from db_bullfight_matchfight "
					+ " where isdelete=0 and status=2 and league_id = unhex('"+leagueId+"') "
					+ " and hostScore>guestScore and host_id = unhex('"+teamId+"');";
			List<Map<String, Object>> rs1 = findByNativeSQL(win1);
			
			String win2 = "select count(id) win2,sum(guestScore) sum2 from db_bullfight_matchfight "
					+ " where isdelete=0 and status=2 and league_id = unhex('"+leagueId+"') "
					+ " and hostScore<guestScore and guest_id = unhex('"+teamId+"');";
			List<Map<String, Object>> rs2 = findByNativeSQL(win2);
			
			String plays = "select count(id) plays from db_bullfight_matchfight "
					+ " where isdelete=0 and status=2 and league_id = unhex('"+leagueId+"') "
					+ " and ( host_id = unhex('"+teamId+"') "
					+ " or guest_id=unhex('"+teamId+"'));";
			List<Map<String, Object>> rsPlays = findByNativeSQL(plays);
			
			
			float winCount = HttpUtil.toFloat(rs1.get(0).get("win1")) + HttpUtil.toFloat(rs2.get(0).get("win2"));
			float playsCount = HttpUtil.toFloat(rsPlays.get(0).get("plays"));
			float loseCount = playsCount - winCount;
			
			float sumCount = HttpUtil.toFloat(rs1.get(0).get("sum1")) + HttpUtil.toFloat(rs2.get(0).get("sum2"));
			
			LeagueRecord entity = findEntity(team, league);
			entity.setWin((int)winCount);
			entity.setLose((int)loseCount);
			entity.setPlays((int)playsCount);
			
			entity.setScore(entity.getWin()*3+entity.getLose()*1);
			entity.setPointSum((int)sumCount);
			entity.setPointAvg((int)(sumCount/playsCount));
			
			saveOrUpdate(entity);
		}
		

		
		
	}
	
	@Transactional
	public LeagueRecord findEntity(Team team,League league)
	{
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("league", league));
		criteria.add(Restrictions.eq("team", team));

		List<LeagueRecord> list = findByCriteria(criteria);
		if(list==null||list.size()==0)
		{
			LeagueRecord leagueRecord = new LeagueRecord();
			leagueRecord.setTeam(team);
			leagueRecord.setLeague(league);
			leagueRecord.setCreatedDate(new Date());
			
			save(leagueRecord);
			
			return leagueRecord;
		}else
		{
			return list.get(0);
		}
		
	}
}