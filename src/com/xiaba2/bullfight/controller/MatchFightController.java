package com.xiaba2.bullfight.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
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

@RestController
@RequestMapping("/matchfight")
public class MatchFightController {
	@Resource
	private MatchFightService matchFightService;

	@Resource
	private TeamService teamService;

	@Resource
	private ArenaService arenaService;

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