package com.xiaba2.bullfight.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.ILeagueRecordDao;
import com.xiaba2.bullfight.domain.League;
import com.xiaba2.bullfight.domain.LeagueRecord;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class LeagueRecordService extends BaseService<LeagueRecord, UUID> {
	@Resource
	private ILeagueRecordDao leagueRecordDao;

	@Override
	protected IBaseDao<LeagueRecord, UUID> getEntityDao() {
		return leagueRecordDao;
	}
	
	
	@Transactional
	public void countRecord(MatchFight matchFight)
	{
		LeagueRecord host = fingEntity(matchFight.getHost(),matchFight.getLeague());
		LeagueRecord guest = fingEntity(matchFight.getGuest(),matchFight.getLeague());
		
		
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
	
	
	private LeagueRecord fingEntity(Team team,League league)
	{
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("league.id", league.getId()));
		criteria.add(Restrictions.eq("team.id", team.getId()));

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