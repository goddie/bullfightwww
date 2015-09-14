package com.xiaba2.bullfight.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.dao.impl.MatchFightDao;
import com.xiaba2.bullfight.domain.MatchDataTeam;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.service.MatchDataTeamService;
import com.xiaba2.bullfight.service.MatchFightService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;

@RestController
@RequestMapping("/matchdatateam")
public class MatchDataTeamController {
	@Resource
	private MatchDataTeamService matchDataTeamService;

	@Resource
	private MatchFightService matchFightService;

	/**
	 * 球队比赛
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/json/teammatch")
	public JsonResult jsonTeamMatch(@RequestParam("tid") String tid) {
		JsonResult rs = new JsonResult();

		if (StringUtils.isEmpty(tid)) {
			return rs;
		}

		DetachedCriteria criteria = matchDataTeamService
				.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("team.id", UUID.fromString(tid)));

		Page<MatchDataTeam> page = new Page<MatchDataTeam>();
		page.setPageNo(1);
		page.setPageSize(20);
		page.addOrder("createdDate", "desc");

		page = matchDataTeamService.findPageByCriteria(criteria, page);

		List<MatchFight> list = new ArrayList<MatchFight>();
		for (MatchDataTeam mdu : page.getResult()) {
			MatchFight mf = mdu.getMatchFight();
			list.add(mf);
		}

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list);

		return rs;
	}

	/**
	 * 队伍数据
	 * 会取出2个队伍的数据
	 * @param mfid
	 * @return
	 */
	@RequestMapping(value = "/json/teamdata")
	public JsonResult jsonTeamData(@RequestParam("mfid") UUID mfid) {
		JsonResult rs = new JsonResult();
		MatchFight matchFight = matchFightService.get(mfid);
		DetachedCriteria criteria = matchDataTeamService
				.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		List<MatchDataTeam> list = matchDataTeamService.findByCriteria(criteria);
		if(list.isEmpty()||list.size()<2)
		{
			return rs;
		}
		
		List<MatchDataTeam> list2=new ArrayList<MatchDataTeam>();
		if (list.get(0).getId().equals(matchFight.getHost().getId())) {
			list2.add(list.get(0));
			list2.add(list.get(1));
		}else
		{
			list2.add(list.get(1));
			list2.add(list.get(0));
		}
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list2);

		return rs;

	}

}