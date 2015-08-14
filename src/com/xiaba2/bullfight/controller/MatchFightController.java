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

	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_matchfight_" + name);
	}

	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList(MatchFight entity, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("admin_matchfight_list");

		Page<MatchFight> page = new Page<MatchFight>();
		page.setPageSize(10);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = matchFightService.createDetachedCriteria();
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
		}
		
		
		
		Arena arena = arenaService.get(UUID.fromString(arenaId));
		
		
		entity.setArena(arena);
		entity.setHost(host);
		
		entity.setCreatedDate(new Date());
		entity.setStatus(0);
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
		entity.setCreatedDate(new Date());
		entity.setStatus(0);
		entity.setIsPay(0);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date dstart;
		Date dend;

		try {

			dstart = sdf.parse(request.getParameter("start"));
			entity.setStart(dstart);

			dend = sdf.parse(request.getParameter("end"));
			entity.setEnd(dend);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		matchFightService.save(entity);

		js.setCode(JsonResult.SUCCESS);
		js.setMsg("创建成功");

		return js;
	}
}