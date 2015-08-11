package com.xiaba2.bullfight.controller;

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

import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;

@RestController
@RequestMapping("/team")
public class TeamController {
	@Resource
	private TeamService teamService;

	@Resource
	private UserService userService;

	@RequestMapping("/json/add")
	public JsonResult jsonAdd(Team entity, HttpServletRequest request) {
		JsonResult js = new JsonResult();

		String uid = request.getParameter("uid");
		User u = userService.get(UUID.fromString(uid));

		if (u == null) {
			js.setMsg("创建失败：用户不存在");
			return js;
		}

		entity.setAdmin(u);
		entity.setCreatedDate(new Date());
		entity.setStatus(1);
		teamService.save(entity);

		if (entity.getId() != null) {
			js.setMsg("创建失败");
			return js;
		}

		js.setCode(JsonResult.SUCCESS);
		js.setMsg("创建成功");

		return js;
	}

	/**
	 * 换人
	 * 
	 * @param tid
	 * @param newid
	 * @param oldid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/admin")
	public JsonResult jsonAdm̋in(@RequestParam("tid") String tid,
			@RequestParam("newid") String newid,
			@RequestParam("oldid") String oldid, HttpServletRequest request) {
		JsonResult js = new JsonResult();

		Team team = teamService.get(UUID.fromString(tid));

		User oldu = userService.get(UUID.fromString(oldid));
		User newu = userService.get(UUID.fromString(newid));

		if (team.getAdmin() == oldu) {
			team.setAdmin(newu);
		}

		js.setCode(JsonResult.SUCCESS);
		js.setMsg("操作成功");

		return js;
	}
}