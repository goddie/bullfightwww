package com.xiaba2.bullfight.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.domain.TeamUser;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.bullfight.service.TeamUserService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;

@RestController
@RequestMapping("/teamuser")
public class TeamUserController {
	@Resource
	private TeamUserService teamUserService;

	@Resource
	private UserService userService;

	@Resource
	private TeamService teamService;

	/**
	 * 加入
	 * 
	 * @param tid
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/join")
	public JsonResult jsonJoin(@RequestParam("tid") String tid,
			@RequestParam("uid") String uid, HttpServletRequest request) {

		JsonResult js = new JsonResult();

		User user = userService.get(UUID.fromString(uid));
		Team team = teamService.get(UUID.fromString(tid));

		if (user == null || team == null) {
			js.setMsg("加入失败");
			return js;
		}

		TeamUser entity = new TeamUser();
		entity.setCreatedDate(new Date());
		entity.setUser(user);
		entity.setTeam(team);
		entity.setStatus(1);

		teamUserService.save(entity);

		js.setCode(JsonResult.SUCCESS);
		js.setMsg("加入失败");

		return js;
	}

	/**
	 * 退出
	 * 
	 * @param tid
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/quit")
	public JsonResult jsonQuit(@RequestParam("tid") String tid,
			@RequestParam("uid") String uid, HttpServletRequest request) {

		JsonResult js = new JsonResult();

		// User user = userService.get(UUID.fromString(uid));
		// Team team = teamService.get(UUID.fromString(tid));
		//
		// if (user==null||team==null) {
		// js.setMsg("加入失败");
		// return js;
		// }

		DetachedCriteria criteria = teamUserService.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", UUID.fromString(uid)));
		criteria.add(Restrictions.eq("team.id", UUID.fromString(tid)));

		List<TeamUser> list = teamUserService.findByCriteria(criteria);
		if (list == null || list.size() == 0) {
			return js;
		}

		// TeamUser entity = new TeamUser();
		// entity.setCreatedDate(new Date());
		// entity.setUser(user);
		// entity.setTeam(team);

		TeamUser entity = list.get(0);
		entity.setStatus(0);
		entity.setLastModifiedDate(new Date());

		teamUserService.saveOrUpdate(entity);

		js.setCode(JsonResult.SUCCESS);
		js.setMsg("成功退出");

		return js;
	}

}