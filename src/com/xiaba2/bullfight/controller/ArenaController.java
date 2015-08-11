package com.xiaba2.bullfight.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.Arena;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.ArenaService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;

@RestController
@RequestMapping("/arena")
public class ArenaController {
	@Resource
	private ArenaService arenaService;
	
	@Resource
	private UserService userService;

	@RequestMapping("/json/add")
	public JsonResult jsonAdd(Arena entity, HttpServletRequest request) {

		JsonResult rs = new JsonResult();
		
		String uid =  request.getParameter("uid");
		
		if (!StringUtils.isEmpty(uid)) {
			User user = userService.get(UUID.fromString(uid));
			entity.setUser(user);
		}
		

		entity.setCreatedDate(new Date());
		entity.setStatus(0);
		arenaService.save(entity);
		
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("添加成功");

		return rs;
	}
	
	
	
	
	

}