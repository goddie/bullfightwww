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

import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.MatchFightUser;
import com.xiaba2.bullfight.domain.PayRecord;
import com.xiaba2.bullfight.domain.TeamUser;
import com.xiaba2.bullfight.service.MatchFightService;
import com.xiaba2.bullfight.service.MatchFightUserService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;

@RestController
@RequestMapping("/matchfightuser")
public class MatchFightUserController {
	@Resource
	private MatchFightUserService matchFightUserService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private MatchFightService matchFightService;
	
	
	@RequestMapping(value = "/json/isjoin")
	public JsonResult jsonIsJoin(@RequestParam("uid") UUID uid,@RequestParam("mfid") UUID mfid, HttpServletRequest request) {
		
		
		JsonResult rs=new JsonResult();
		User user  = userService.get(uid);
		MatchFight matchFight = matchFightService.get(mfid);
		
		DetachedCriteria criteria = matchFightUserService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		
		List<MatchFightUser> list = matchFightUserService.findByCriteria(criteria);
		if(list!=null&&list.size()>0)
		{
			rs.setData(list.get(0));
			rs.setCode(JsonResult.SUCCESS);
			return rs;
		} 
		
		return rs;
	}
	
	
	@RequestMapping(value = "/json/join")
	public JsonResult jsonJoin(@RequestParam("uid") UUID uid,@RequestParam("mfid") UUID mfid, HttpServletRequest request) {
		
		
		JsonResult rs=new JsonResult();
 
		
		User user  = userService.get(uid);
		
		MatchFight matchFight = matchFightService.get(mfid);
		
		DetachedCriteria criteria = matchFightUserService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		
		List<MatchFightUser> list = matchFightUserService.findByCriteria(criteria);
		if(list!=null&&list.size()>0)
		{
			rs.setData(list.get(0));
			rs.setCode(JsonResult.SUCCESS);
			rs.setMsg("报名成功!");
			return rs;
		} 
			
 
		
		MatchFightUser entity = new MatchFightUser();
		entity.setUser(user);
		entity.setMatchFight(matchFight);
		entity.setCreatedDate(new Date());
		
		matchFightUserService.save(entity);
		
		rs.setMsg("报名成功!");
		rs.setCode(JsonResult.SUCCESS);

		
		return rs;
	}
	
	
	/**
	 * 已报名用户分页
	 * @param mfid
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/listuser")
	public JsonResult jsonListUser(@RequestParam("mfid") UUID mfid,@RequestParam("count") int count, HttpServletRequest request) {
		
		
		JsonResult rs=new JsonResult();
		
		MatchFight matchFight = matchFightService.get(mfid);
		
		DetachedCriteria criteria = matchFightUserService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		
		
		Page<MatchFightUser> page = new Page<MatchFightUser>();
		page.setPageNo(1);
		page.setPageSize(count);
		page.addOrder("createdDate", "desc");
		
		page = matchFightUserService.findPageByCriteria(criteria, page);
		
		if(page.getResult().size()==0)
		{
			return rs;
		}
		
		List<User> userList = new ArrayList<User>();
		
		for (MatchFightUser mfu : page.getResult()) {
			userList.add(mfu.getUser());
		}
		
		rs.setData(userList);
 
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
	}
	
	/**
	 * 已报名用户分页
	 * @param mfid
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("mfid") UUID mfid,@RequestParam("p") int p, HttpServletRequest request) {
		
		
		JsonResult rs=new JsonResult();
		
		MatchFight matchFight = matchFightService.get(mfid);
		
		DetachedCriteria criteria = matchFightUserService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		
		
		Page<MatchFightUser> page = new Page<MatchFightUser>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");
		
		page = matchFightUserService.findPageByCriteria(criteria, page);
		
		
		rs.setData(page.getResult());
 
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
	}
	
	
	
}