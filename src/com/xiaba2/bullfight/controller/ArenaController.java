package com.xiaba2.bullfight.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.Arena;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.ArenaService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;

@RestController
@RequestMapping("/arena")
public class ArenaController {
	@Resource
	private ArenaService arenaService;
	
	@Resource
	private UserService userService;
	
	
	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_arena_" + name);
	}
	
	
	@RequestMapping(value = "/add")
	public ModelAndView add(Arena entity, HttpServletRequest request) {
		
		
		ModelAndView mv=new ModelAndView("admin_arena_add");
		
		entity.setCreatedDate(new Date());
		
		arenaService.save(entity);
		
		return mv;
	}
	
	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList() {
		
		
		ModelAndView mv=new ModelAndView("admin_arena_list");
		
		Page<Arena> page = new Page<Arena>();
		page.setPageSize(10);
		page.setPageNo(1);
		
		DetachedCriteria criteria = arenaService.createDetachedCriteria();
		page = arenaService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		
		return mv;
	}
	
	
	@RequestMapping(value = "/admin/sel")
	public ModelAndView pageSel() {
		
		
		ModelAndView mv=new ModelAndView("admin_arena_sel");
		
		Page<Arena> page = new Page<Arena>();
		page.setPageSize(10);
		page.setPageNo(1);
		
		DetachedCriteria criteria = arenaService.createDetachedCriteria();
		page = arenaService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		
		return mv;
	}

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