package com.xiaba2.bullfight.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xiaba2.bullfight.dao.IMatchDataTeamDao;
import com.xiaba2.bullfight.dao.IMatchFightDao;
import com.xiaba2.bullfight.domain.MatchDataTeam;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.cms.domain.User;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class MatchDataTeamService extends BaseService<MatchDataTeam, UUID> {
	@Resource
	private IMatchDataTeamDao matchDataTeamDao;
	
	private IMatchFightDao matchFightDao;

	@Override
	protected IBaseDao<MatchDataTeam, UUID> getEntityDao() {
		return matchDataTeamDao;
	}

	/**
	 * 调整队伍的成绩
	 * 
	 * @param matchFight
	 * @param user
	 */
	@Transactional
	public void updateTeamByUser(MatchDataUser matchDataUser,int add)
	{
		DetachedCriteria criteria = matchDataTeamDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchDataUser.getMatchFight()));
		criteria.add(Restrictions.eq("team", matchDataUser.getTeam()));
		//criteria.add(Restrictions.eq("user", matchDataUser.getUser()));
		
		MatchDataTeam entity = new MatchDataTeam();
		
		List<MatchDataTeam> list = matchDataTeamDao.findByCriteria(criteria);
		if(list!=null&&list.size()>0)
		{
			entity = list.get(0);
		}
		
		entity.setMatchFight(matchDataUser.getMatchFight());
		entity.setTeam(matchDataUser.getTeam());
		entity.setLastModifiedDate(new Date());
		entity.setScoring(entity.getScoring()+matchDataUser.getScoring()*add);
		entity.setRebound(entity.getRebound()+matchDataUser.getRebound()*add);
		entity.setAssist(entity.getAssist()+matchDataUser.getAssist()*add);
		entity.setBlock(entity.getBlock()+matchDataUser.getBlock()*add);
		entity.setSteal(entity.getSteal()+matchDataUser.getSteal()*add);
		entity.setTurnover(entity.getTurnover()+matchDataUser.getTurnover()*add);
		
		//entity.setCreatedDate(new Date());
		
		matchDataTeamDao.saveOrUpdate(entity);
		

		
	}
}