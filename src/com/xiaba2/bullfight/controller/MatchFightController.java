package com.xiaba2.bullfight.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.Arena;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.ArenaService;
import com.xiaba2.bullfight.service.MatchFightService;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;

@RestController
@RequestMapping("/matchfight")
public class MatchFightController {
	@Resource
	private MatchFightService matchFightService;

	@Resource
	private TeamService teamService;

	@Resource
	private ArenaService arenaService;
	
	@Resource
	private UserService userService;

	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_matchfight_" + name);
	}

	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList(MatchFight entity, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("admin_matchfight_list");

		Page<MatchFight> page = new Page<MatchFight>();
		page.setPageSize(999);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = matchFightService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		page = matchFightService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(MatchFight entity, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_matchfight_add");

		String hostTeamId = request.getParameter("hostteam");
		String guestTeamId = request.getParameter("guestteam");
		String arenaId = request.getParameter("arenaid");
		
		if(StringUtils.isEmpty(hostTeamId)||StringUtils.isEmpty(arenaId))
		{
			return mv;
		}
		
		Team host = teamService.get(UUID.fromString(hostTeamId));

		if(!StringUtils.isEmpty(guestTeamId))
		{
			Team guest = teamService.get(UUID.fromString(guestTeamId));
			entity.setGuest(guest);
			entity.setStatus(1);
		}
		
		
		
		
		Arena arena = arenaService.get(UUID.fromString(arenaId));
		
		
		entity.setArena(arena);
		entity.setHost(host);
		
		entity.setCreatedDate(new Date());
		//entity.setStatus(0);
		entity.setIsPay(0);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

		Date dstart;
		Date dend;

		try {

			dstart = sdf.parse(request.getParameter("startDateStr"));
			entity.setStart(dstart);

			dend = sdf.parse(request.getParameter("endDateStr"));
			entity.setEnd(dend);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		matchFightService.save(entity);


		return mv;
	}

	/**
	 * 创建
	 * 
	 * @param entity
	 * @param tid
	 * @param aid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/add")
	public JsonResult jsonAdd(MatchFight entity,
			@RequestParam("tid") String tid, @RequestParam("aid") String aid,
			HttpServletRequest request) {
		JsonResult js = new JsonResult();

		Team team = teamService.get(UUID.fromString(tid));
		Arena arena = arenaService.get(UUID.fromString(aid));

		if (team == null || arena == null) {
			return js;
		}

		
		entity.setHost(team);
		entity.setArena(arena);
		entity.setCreatedDate(new Date());
		
		
		entity.setStatus(0);
		entity.setIsPay(1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date dstart;
		Date dend;

		try {

			dstart = sdf.parse(request.getParameter("startStr"));
			entity.setStart(dstart);

			dend = sdf.parse(request.getParameter("endStr"));
			entity.setEnd(dend);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		matchFightService.save(entity);

		js.setCode(JsonResult.SUCCESS);
		js.setMsg("创建成功");

		return js;
	}
	
	/**
	 * 筛选比赛
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/matchlist")
	public JsonResult jsonMatchList(HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		
		DetachedCriteria criteria = matchFightService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		Page<MatchFight> page = new Page<MatchFight>();
		page.setPageSize(10);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");

		
		
		//比赛类型
		int matchType = 1;
		String mt = request.getParameter("matchType");
		if(!StringUtils.isEmpty(mt))
		{
			matchType = Integer.parseInt(mt);
			criteria.add(Restrictions.eq("matchType", matchType));
		}
		
		//比赛状态
		int status = -1;
		String st = request.getParameter("status");
		if(!StringUtils.isEmpty(st))
		{
			status = Integer.parseInt(st);
			if(status!=-1)
			{
				criteria.add(Restrictions.eq("status", status));
			}
			
		}
		
		//城市
		String city = request.getParameter("city");
		if(!StringUtils.isEmpty(city))
		{
			criteria.add(Restrictions.eq("city", city));
		}
		
		
		
		String pg = request.getParameter("page");
		if(!StringUtils.isEmpty(pg))
		{
			int p = Integer.parseInt(pg);
			page.setPageNo(p);
		}
		
		
		page = matchFightService.findPageByCriteria(criteria, page);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());
		
		return rs;
	}
	
	/**
	 * 
	 * 结束比赛
	 * @param mfid
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/finish")
	public JsonResult jsonFinish(@RequestParam("mfid") UUID mfid, HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		
		MatchFight matchFight = matchFightService.get(mfid);
		
		Team host = matchFight.getHost();
		Team guest = matchFight.getGuest();

		if (host == null || guest == null) {
			rs.setMsg("比赛双方不齐");
			return rs;
		}

		if (matchFight.getHostScore() == 0
				|| matchFight.getGuestScore() == 0) {
			rs.setMsg("比赛双方比分不正确");
			return rs;
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

		teamService.saveOrUpdate(host);
		teamService.saveOrUpdate(guest);
		
		matchFight.setStatus(2);
		matchFightService.saveOrUpdate(matchFight);
		
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("操作成功");
		return rs;
	}
	
 
	/**
	 * 
	 * 应战
	 * @param mfid
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/accept")
	public JsonResult jsonAccept(@RequestParam("mfid") String mfid, @RequestParam("uid") String uid,
			HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		MatchFight matchFight = matchFightService.get(UUID.fromString(mfid));
		User user = userService.get(UUID.fromString(uid));

		if (matchFight == null || user == null) {
			return rs;
		}

		
		DetachedCriteria criteria = teamService.createDetachedCriteria();
		criteria.add(Restrictions.eq("admin.id", user.getId()));
		criteria.add(Restrictions.eq("isDelete", 0));
		List<Team> list = teamService.findByCriteria(criteria);
		
		if(list.isEmpty())
		{
			rs.setMsg("你不是队长");
			return rs;
		}
		 
		matchFight.setGuest(list.get(0));
		matchFight.setStatus(1);
		matchFight.setLastModifiedDate(new Date());
		
		matchFightService.saveOrUpdate(matchFight);

		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("操作成功");

		return rs;
	}
	
}