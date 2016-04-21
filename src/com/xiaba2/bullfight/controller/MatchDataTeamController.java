package com.xiaba2.bullfight.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.MatchDataTeamService;
import com.xiaba2.bullfight.service.MatchFightService;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/matchdatateam")
public class MatchDataTeamController {
	@Resource
	private MatchDataTeamService matchDataTeamService;

	@Resource
	private MatchFightService matchFightService;

	@Resource
	private TeamService teamService;

	/**
	 * 球队比赛
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/json/teammatch")
	public JsonResult jsonTeamMatch(@RequestParam("tid") UUID tid,HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		int p = 1;
		
		Team team = teamService.get(tid);

		String str = request.getParameter("page");
		if(!StringUtils.isEmpty(str))
		{
			p = Integer.parseInt(str);
		}
		
		DetachedCriteria criteria = matchDataTeamService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("team", team));

		Page<MatchDataTeam> page = new Page<MatchDataTeam>();
		page.setPageNo(p);
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
	 * 队伍数据 会取出2个队伍的数据
	 * 
	 * @param mfid
	 * @return
	 */
	@RequestMapping(value = "/json/teamdata")
	public JsonResult jsonTeamData(@RequestParam("mfid") UUID mfid) {
		JsonResult rs = new JsonResult();
		MatchFight matchFight = matchFightService.get(mfid);
		DetachedCriteria criteria = matchDataTeamService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		List<MatchDataTeam> list = matchDataTeamService.findByCriteria(criteria);
		if (list.isEmpty() || list.size() < 2) {
			return rs;
		}

		List<MatchDataTeam> list2 = new ArrayList<MatchDataTeam>();
		if (list.get(0).getId().equals(matchFight.getHost().getId())) {
			list2.add(list.get(0));
			list2.add(list.get(1));
		} else {
			list2.add(list.get(1));
			list2.add(list.get(0));
		}

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list2);

		return rs;

	}

	@RequestMapping(value = "/admin/list")
	public ModelAndView adminList(@RequestParam("mfid") UUID mfid) {
		ModelAndView mv = new ModelAndView("admin_matchdatateam_list");
		MatchFight matchFight = matchFightService.get(mfid);
		DetachedCriteria criteria = matchDataTeamService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		List<MatchDataTeam> list = matchDataTeamService.findByCriteria(criteria);

		mv.addObject("list", list);
		return mv;

	}

	@RequestMapping(value = "/admin/count")
	public ModelAndView countTeamData(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		DetachedCriteria criteria1 = teamService.createDetachedCriteria();
		criteria1.add(Restrictions.eq("isDelete", 0));
//		criteria1.add(Restrictions.eq("id",UUID.fromString("f644c4e6-6ae9-44e3-9faf-9ba3a3ebfbf6")));

		List<Team> list1 = teamService.findByCriteria(criteria1);
		for (Team team : list1) {
			DetachedCriteria criteria = matchFightService.createDetachedCriteria();
			criteria.add(Restrictions.eq("isDelete", 0));
			criteria.add(Restrictions.eq("status", 2));
			criteria.add(Restrictions.or(Restrictions.eq("host", team), Restrictions.eq("guest", team)));

			List<MatchFight> list = matchFightService.findByCriteria(criteria);

			for (MatchFight matchFight : list) {

				// 更新对战队伍单场数据
				matchDataTeamService.updateMatchTeam(matchFight, team);

				// 更新对战队伍总体数据
				teamService.updateData(team);
				
				teamService.countWinLose(team);
				
				matchFightService.updateScore(matchFight);
				
			}

		}

		mv.setViewName(HttpUtil.getHeaderRef(request));
		return mv;
	}

}