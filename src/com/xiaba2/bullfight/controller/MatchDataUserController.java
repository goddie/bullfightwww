package com.xiaba2.bullfight.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.mysql.fabric.xmlrpc.base.Array;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.MatchDataTeamService;
import com.xiaba2.bullfight.service.MatchDataUserService;
import com.xiaba2.bullfight.service.MatchFightService;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.bullfight.service.TeamUserService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;

@RestController
@RequestMapping("/matchdatauser")
public class MatchDataUserController {
	@Resource
	private MatchDataUserService matchDataUserService;

	@Resource
	private MatchFightService matchFightService;

	@Resource
	private TeamService teamService;

	@Resource
	private UserService userService;

	@Resource
	private MatchDataTeamService matchDataTeamService;
	
	@Resource
	private TeamUserService teamUserService;

	/**
	 * 录入页面
	 * 
	 * @param mfid
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/admin/add")
	public ModelAndView adminAdd(@RequestParam("mfid") UUID mfid,
			@RequestParam("tid") UUID tid) {
		ModelAndView mv = new ModelAndView("admin_matchdatauser_add");

		MatchFight matchFight = matchFightService.get(mfid);
		Team team = teamService.get(tid);

		mv.addObject("matchFight", matchFight);
		mv.addObject("team", team);

		DetachedCriteria criteria = matchDataUserService
				.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		criteria.add(Restrictions.eq("team", team));
		


//		List<User> list1 = teamUserService.getUsersByTeam(team);
//		
//		List<UUID> ids = new ArrayList<UUID>();
//		for (User user : list1) {
//			ids.add(user.getId());
//		}
//		
//		criteria.add(Restrictions.in("user.id", ids));
		
		
		List<MatchDataUser> list = matchDataUserService
				.findByCriteria(criteria);
		
		
		
		
		mv.addObject("list", list);

		return mv;
	}

	/**
	 * 录入一个球员数据
	 * 
	 * @param entity
	 * @param uid
	 * @param mfid
	 * @param tid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(MatchDataUser entity,
			@RequestParam("uid") UUID uid, @RequestParam("mfid") UUID mfid,
			@RequestParam("tid") UUID tid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(
				"redirect:/matchdatauser/admin/add?mfid=" + mfid.toString()
						+ "&tid=" + tid.toString());
		



		

		if (entity.getShot() > 0) {
			entity.setGoalPercent(entity.getGoal() / entity.getShot());
		}

		if (entity.getThreeShot() > 0) {
			entity.setThreeGoalPercent(entity.getThreeGoal()
					/ entity.getThreeShot());
		}

		if (entity.getFree() > 0) {
			entity.setFreeGoalPercent(entity.getFreeGoal() / entity.getFree());
		}

		Team team = teamService.get(tid);
		entity.setTeam(team);
		
		MatchFight matchFight = matchFightService.get(mfid);
		entity.setMatchFight(matchFight);

		User user = userService.get(uid);
		entity.setUser(user);
		
		
		DetachedCriteria criteria = matchDataUserService
				.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("team", team));
		

		entity.setCreatedDate(new Date());


		List<MatchDataUser> list = matchDataUserService
				.findByCriteria(criteria);
		
		if (list != null && list.size() > 0) {
			matchDataUserService.delete(list.get(0));
		}

		matchDataUserService.save(entity);
		
		matchDataTeamService.updateTeamByUser(entity, 1);

		
		if(team.getId().equals(matchFight.getHost().getId()))
		{
			matchFight.setHostScore(entity.getScoring());
		}
		
		if(team.getId().equals(matchFight.getGuest().getId()))
		{
			matchFight.setGuestScore(entity.getScoring());
		}
		
		matchFightService.saveOrUpdate(matchFight);
		
		
		return mv;
	}

	/**
	 * 球员比赛
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/json/usermatch")
	public JsonResult jsonUserMatch(@RequestParam("uid") String uid,@RequestParam("p") int p,HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		if (StringUtils.isEmpty(uid)) {
			return rs;
		}

		DetachedCriteria criteria = matchDataUserService
				.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", UUID.fromString(uid)));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		
//		String statusStr =  request.getParameter("status");
//		if(!StringUtils.isEmpty(statusStr))
//		{
//			criteria.add(Restrictions.eq("matchFight.status", Integer.parseInt(statusStr)));
//		}

		Page<MatchDataUser> page = new Page<MatchDataUser>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");

		page = matchDataUserService.findPageByCriteria(criteria, page);

		List<MatchFight> list = new ArrayList<MatchFight>();
		for (MatchDataUser mdu : page.getResult()) {
			MatchFight mf = mdu.getMatchFight();
			list.add(mf);
		}

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list);

		return rs;
	}
	
	
	/**
	 * 上场球员详细数据
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/json/userdata")
	public JsonResult jsonUserData(@RequestParam("mfid") UUID mfid) {
		JsonResult rs = new JsonResult();
		
		MatchFight matchFight = matchFightService.get(mfid);
		
		Team host = matchFight.getHost();
		Team guest = matchFight.getGuest();
		
		DetachedCriteria criteria1 = matchDataUserService.createDetachedCriteria();
		criteria1.add(Restrictions.eq("isDelete",0));
		criteria1.add(Restrictions.eq("team", host));
		criteria1.add(Restrictions.eq("matchFight",matchFight));
		List<MatchDataUser> list1 = matchDataUserService.findByCriteria(criteria1);
		
		DetachedCriteria criteria2 = matchDataUserService.createDetachedCriteria();
		criteria2.add(Restrictions.eq("isDelete",0));
		criteria2.add(Restrictions.eq("team", guest));
		criteria2.add(Restrictions.eq("matchFight",matchFight));
		List<MatchDataUser> list2 = matchDataUserService.findByCriteria(criteria2);
		
		
		List<List<MatchDataUser>> listBig = new ArrayList<List<MatchDataUser>>();
		listBig.add(list1);
		listBig.add(list2);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(listBig);

		
		return rs;
	}
}