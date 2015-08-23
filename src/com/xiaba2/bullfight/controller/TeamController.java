package com.xiaba2.bullfight.controller;

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

import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.domain.TeamUser;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.bullfight.service.TeamUserService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;

@RestController
@RequestMapping("/team")
public class TeamController {
	@Resource
	private TeamService teamService;

	@Resource
	private UserService userService;
	
	@Resource
	private TeamUserService teamUserService;
	
	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_team_" + name);
	}
	
	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList() {
		
		
		ModelAndView mv=new ModelAndView("admin_team_list");
		
		Page<Team> page = new Page<Team>();
		page.setPageSize(999);
		page.setPageNo(1);
		
		DetachedCriteria criteria = teamService.createDetachedCriteria();
		page = teamService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		
		return mv;
	}
	
	
	@RequestMapping(value = "/admin/recordlist")
	public ModelAndView pageRecordList() {
		
		
		ModelAndView mv=new ModelAndView("admin_team_recordlist");
		
		Page<Team> page = new Page<Team>();
		page.setPageSize(10);
		page.setPageNo(1);
		
		DetachedCriteria criteria = teamService.createDetachedCriteria();
		page = teamService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		
		return mv;
	}
	
	@RequestMapping(value = "/admin/sel")
	public ModelAndView pageSel() {
		
		
		ModelAndView mv=new ModelAndView("admin_team_sel");
		
		Page<Team> page = new Page<Team>();
		page.setPageSize(999);
		page.setPageNo(1);
		
		DetachedCriteria criteria = teamService.createDetachedCriteria();
		page = teamService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		
		return mv;
	}
	
	
	
	/** 
	 *  球队数据
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/admin/uprecord")
	public ModelAndView adminUprecord(@RequestParam("tid") UUID tid) {
		ModelAndView mv=new ModelAndView("admin_team_uprecord");
		
		Team entity = teamService.get(tid);
		
		mv.addObject("entity", entity);
		
		return mv;
	}
	
	
	
	
	
	
	@RequestMapping("/add")
	public ModelAndView add(Team entity, HttpServletRequest request) {
		ModelAndView mv=new ModelAndView("redirect:admin/add");

		String uid = request.getParameter("uid");
		User u = userService.get(UUID.fromString(uid));

		TeamUser teamUser=null;
		
		if (u != null) {
			entity.setAdmin(u);
			
			teamUser =new TeamUser();
			teamUser.setUser(u);
			teamUser.setTeam(entity);
			teamUser.setCreatedDate(new Date());
		}


		entity.setCreatedDate(new Date());
		entity.setStatus(1);
		teamService.save(entity);
		if(teamUser!=null)
		{
			teamUserService.save(teamUser);
		}
		

		return mv;
	}

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

		if (team.getAdmin().getId().toString().equals(oldu.getId().toString())) {
			team.setAdmin(newu);
			
			teamService.saveOrUpdate(team);

		}

		js.setCode(JsonResult.SUCCESS);
		js.setMsg("操作成功");

		return js;
	}
	
	@RequestMapping("/json/getteam")
	public JsonResult jsonGetTeam(@RequestParam("tid") String tid) {
		JsonResult js = new JsonResult();

		if(StringUtils.isEmpty(tid))
		{
			return js;
		}
		
		Team team = teamService.get(UUID.fromString(tid));
		js.setData(team);
		js.setCode(JsonResult.SUCCESS);
		return js;
	}
	
	
	/**
	 * 用户是否队长
	 * @param uid
	 * @return
	 */
	@RequestMapping("/json/checkuser")
	public JsonResult jsonCheckUser(@RequestParam("uid") String uid)
	{
		JsonResult rs = new JsonResult();
		
		DetachedCriteria criteria = teamService.createDetachedCriteria();
		criteria.add(Restrictions.eq("admin.id", UUID.fromString(uid)));
		
		List<Team> list = teamService.findByCriteria(criteria);
		
		if(!list.isEmpty())
		{
			rs.setCode(JsonResult.SUCCESS);
			return rs;
		}
		
		
		return rs;
	}
	
	
}