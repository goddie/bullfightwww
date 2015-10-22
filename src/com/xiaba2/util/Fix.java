package com.xiaba2.util;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.xiaba2.bullfight.domain.MatchDataTeam;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.MatchDataTeamService;
import com.xiaba2.bullfight.service.MatchDataUserService;
import com.xiaba2.bullfight.service.MatchFightService;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.cms.service.UserService;

public class Fix {
	public static void fix() {
		MatchDataUserService service = ToolSpring.getApplicationContext()
				.getBean(MatchDataUserService.class);
		MatchDataTeamService service2 = ToolSpring.getApplicationContext()
				.getBean(MatchDataTeamService.class);
		MatchFightService service3 = ToolSpring.getApplicationContext()
				.getBean(MatchFightService.class);

		List<MatchDataUser> list = service.loadAll();

		for (MatchDataUser matchDataUser : list) {

			DetachedCriteria criteria = service2.createDetachedCriteria();
			criteria.add(Restrictions.eq("isDelete", 0));
			criteria.add(Restrictions.eq("matchFight",
					matchDataUser.getMatchFight()));
			criteria.add(Restrictions.eq("team", matchDataUser.getTeam()));
			// criteria.add(Restrictions.eq("user", matchDataUser.getUser()));

			MatchDataTeam entity = new MatchDataTeam();

			List<MatchDataTeam> list2 = service2.findByCriteria(criteria);
			if (list2 != null && list2.size() > 0) {
				entity = list2.get(0);
			}

			int add = 1;

			entity.setMatchFight(matchDataUser.getMatchFight());
			entity.setTeam(matchDataUser.getTeam());
			entity.setLastModifiedDate(new Date());
			entity.setScoring(entity.getScoring() + matchDataUser.getScoring()
					* add);
			entity.setRebound(entity.getRebound() + matchDataUser.getRebound()
					* add);
			entity.setAssist(entity.getAssist() + matchDataUser.getAssist()
					* add);
			entity.setBlock(entity.getBlock() + matchDataUser.getBlock() * add);
			entity.setSteal(entity.getSteal() + matchDataUser.getSteal() * add);
			entity.setTurnover(entity.getTurnover()
					+ matchDataUser.getTurnover() * add);

			// entity.setCreatedDate(new Date());

			service2.saveOrUpdate(entity);

			Team t = matchDataUser.getTeam();
			MatchFight mf = entity.getMatchFight();

			if (t.getId().equals(mf.getHost().getId())) {
				mf.setHostScore(entity.getScoring());
			}

			if (t.getId().equals(mf.getGuest().getId())) {
				mf.setGuestScore(entity.getScoring());
			}

			service3.saveOrUpdate(mf);

		}

	}

	public static void fix2() {
		MatchDataUserService service = ToolSpring.getApplicationContext()
				.getBean(MatchDataUserService.class);
		MatchDataTeamService service2 = ToolSpring.getApplicationContext()
				.getBean(MatchDataTeamService.class);
		MatchFightService service3 = ToolSpring.getApplicationContext()
				.getBean(MatchFightService.class);

		TeamService service4 = ToolSpring.getApplicationContext().getBean(
				TeamService.class);

		List<MatchFight> list = service3.loadAll();
		for (MatchFight matchFight : list) {

			Team host = matchFight.getHost();
			Team guest = matchFight.getGuest();

			if (host == null || guest == null) {
				continue;
			}

			if (matchFight.getHostScore() == 0
					|| matchFight.getGuestScore() == 0) {
				continue;
			}
			
			

			if (matchFight.getHostScore() > matchFight.getGuestScore()) {
				matchFight.setWinner(matchFight.getHost());
				host.setWin(host.getWin() + 1);
				guest.setLose(guest.getLose() + 1);

			}

			if (matchFight.getHostScore() < matchFight.getGuestScore()) {
				matchFight.setWinner(matchFight.getGuest());

				guest.setWin(guest.getWin() + 1);
				host.setLose(host.getLose() + 1);
			}

			if (matchFight.getHostScore() == matchFight.getGuestScore()) {
				matchFight.setDraw(1);
				guest.setDraw(guest.getDraw() + 1);
				host.setDraw(host.getDraw() + 1);

			}

			service4.saveOrUpdate(host);
			service4.saveOrUpdate(guest);
			
			matchFight.setStatus(2);
			service3.saveOrUpdate(matchFight);

		}

	}
}
