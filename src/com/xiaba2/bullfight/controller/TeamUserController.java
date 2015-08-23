package com.xiaba2.bullfight.controller;

import java.util.ArrayList;
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
import com.xiaba2.core.Page;

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
		
		DetachedCriteria criteria = teamUserService.createDetachedCriteria();
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("team", team));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		List<TeamUser> list = teamUserService.findByCriteria(criteria);
		if(!list.isEmpty())
		{
			js.setMsg("已经加入了");
			return js;
		}
		

		TeamUser entity = new TeamUser();
		entity.setCreatedDate(new Date());
		entity.setUser(user);
		entity.setTeam(team);
		entity.setStatus(1);

		teamUserService.save(entity);

		js.setCode(JsonResult.SUCCESS);
		js.setMsg("加入成功");

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

		 User user = userService.get(UUID.fromString(uid));
		 Team team = teamService.get(UUID.fromString(tid));
		 
		 if(team.getAdmin().getId().toString().equals(user.getId().toString()))
		 {
			 js.setMsg("你是队长，不能退队.");
			 return js;
		 }
		 
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
		entity.setIsDelete(1);
		entity.setLastModifiedDate(new Date());
		
//		teamUserService.delete(entity);

		teamUserService.saveOrUpdate(entity);

		js.setCode(JsonResult.SUCCESS);
		js.setMsg("成功退出");

		return js;
	}
	
	
	
	/**
	 * 阵容
	 * @param tid
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/memberlist")
	public JsonResult jsonMemberList(@RequestParam("tid") String tid) {

		JsonResult js = new JsonResult();

 
		Team team = teamService.get(UUID.fromString(tid));
		DetachedCriteria criteria = teamUserService.createDetachedCriteria();
		criteria.add(Restrictions.eq("team.id", UUID.fromString(tid)));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		Page<TeamUser> page = new Page<TeamUser>();
		page.setPageNo(1);
		page.setPageSize(999);
		page.addOrder("createdDate", "desc");
		
		page = teamUserService.findPageByCriteria(criteria, page);
		
		List<User> list =new ArrayList<User>();
		
		for (TeamUser tu : page.getResult()) {
			
			list.add(tu.getUser());
			
		}
		
		js.setData(list);
		js.setCode(JsonResult.SUCCESS);
 

		return js;
	}
	
	
	
	
	
	/**
	 * 两队成员一起显示
	 * @param hostid
	 * @param guestid
	 * @return
	 */
	@RequestMapping("/json/memberlistboth")
	public JsonResult jsonMemberListBoth(@RequestParam("hostid") UUID hostid,@RequestParam("guestid") UUID guestid) {

		JsonResult js = new JsonResult();

		
		List<List<User>> listbig =new ArrayList<List<User>>();
 
		DetachedCriteria criteria = teamUserService.createDetachedCriteria();
		criteria.add(Restrictions.eq("team.id", hostid));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		Page<TeamUser> page = new Page<TeamUser>();
		page.setPageNo(1);
		page.setPageSize(999);
		page.addOrder("createdDate", "desc");
		
		page = teamUserService.findPageByCriteria(criteria, page);
		
		List<User> list =new ArrayList<User>();
		
		for (TeamUser tu : page.getResult()) {
			list.add(tu.getUser());
		}
		
		listbig.add(list);
		
		

		 
 
		DetachedCriteria criteria2 = teamUserService.createDetachedCriteria();
		criteria2.add(Restrictions.eq("team.id", guestid));
		criteria2.add(Restrictions.eq("isDelete", 0));
		
		Page<TeamUser> page2 = new Page<TeamUser>();
		page2.setPageNo(1);
		page2.setPageSize(999);
		page2.addOrder("createdDate", "asc");
		
		page2 = teamUserService.findPageByCriteria(criteria2, page2);
		
		List<User> list2 =new ArrayList<User>();
		
		for (TeamUser tu : page2.getResult()) {
			list2.add(tu.getUser());
		}
		
		listbig.add(list2);
		

		
		js.setData(listbig);
		js.setCode(JsonResult.SUCCESS);
 

		return js;
	}
	
	/**
	 * 我的队伍
	 * @param uid
	 * @return
	 */
	@RequestMapping("/json/myteam")
	public JsonResult jsonMyTeam(@RequestParam("uid") String uid) {

		JsonResult js = new JsonResult();

		//User user = userService.get(UUID.fromString(uid));
		DetachedCriteria criteria = teamUserService.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", UUID.fromString(uid)));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		Page<TeamUser> page = new Page<TeamUser>();
		page.setPageNo(1);
		page.setPageSize(999);
		page.addOrder("createdDate", "desc");
		
		page = teamUserService.findPageByCriteria(criteria, page);
		
		List<Team> list =new ArrayList<Team>();
		
		for (TeamUser tu : page.getResult()) {
			
			list.add(tu.getTeam());
			
		}
		
		js.setData(list);
		js.setCode(JsonResult.SUCCESS);
 

		return js;
	}
	
	/**
	 * 我管理的球队
	 * @param uid
	 * @return
	 */
	@RequestMapping("/json/mymanateam")
	public JsonResult jsonMyManaTeam(@RequestParam("uid") String uid) {

		JsonResult js = new JsonResult();

		//User user = userService.get(UUID.fromString(uid));
		DetachedCriteria criteria = teamService.createDetachedCriteria();
		criteria.add(Restrictions.eq("admin.id", UUID.fromString(uid)));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		Page<Team> page = new Page<Team>();
		page.setPageNo(1);
		page.setPageSize(999);
		page.addOrder("createdDate", "desc");
		
		page = teamService.findPageByCriteria(criteria, page);
		
		
		js.setData(page.getResult());
		js.setCode(JsonResult.SUCCESS);
 

		return js;
	}
	

}