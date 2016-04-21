package com.xiaba2.bullfight.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.Arena;
import com.xiaba2.bullfight.domain.KeyValue;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.ArenaService;
import com.xiaba2.bullfight.service.KeyValueService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/arena")
public class ArenaController {
	@Resource
	private ArenaService arenaService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private KeyValueService keyValueService;
	
	
	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_arena_" + name);
	}
	
	
	@RequestMapping(value = "/del")
	public ModelAndView del(@RequestParam("id") UUID id, HttpServletRequest request) {
		
		
		ModelAndView mv=new ModelAndView("redirect:/arena/admin/list?p=1");
		
		Arena entity = arenaService.get(id);
		entity.setIsDelete(1);
		arenaService.saveOrUpdate(entity);
		
		return mv;
	}
	
	@RequestMapping(value = "/add")
	public ModelAndView add(Arena entity, HttpServletRequest request) {
		
		
		ModelAndView mv=new ModelAndView("admin_arena_add");
		
		entity.setCreatedDate(new Date());
		
		arenaService.save(entity);
		
		return mv;
	}
	
	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList(@RequestParam("p") int p, HttpServletRequest request) {
		
		
		ModelAndView mv=new ModelAndView("admin_arena_list");
		
		Page<Arena> page = new Page<Arena>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		
		DetachedCriteria criteria = arenaService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		HttpUtil.addSearchLike(criteria, mv, request, "name");
		HttpUtil.addSearchLike(criteria, mv, request, "address");
		
		page = arenaService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml",page.genPageHtml(request));
		
		return mv;
	}
	
	
	@RequestMapping(value = "/admin/sel")
	public ModelAndView pageSel(HttpServletRequest request) {
		
		
		ModelAndView mv=new ModelAndView("admin_arena_sel");
		
		Page<Arena> page = new Page<Arena>();
		page.setPageSize(999);
		page.setPageNo(1);
		
		DetachedCriteria criteria = arenaService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		String s = request.getParameter("key");
		if(!StringUtils.isEmpty(s))
		{
			criteria.add(Restrictions.or(Restrictions.like("name", s, MatchMode.ANYWHERE),Restrictions.like("address", s, MatchMode.ANYWHERE)));
		}
		
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
	
	
	/**
	 * 列表
	 * @param p
	 * @return
	 */
	@RequestMapping("/json/list")
	public JsonResult jsonList(@RequestParam("p") int p,@RequestParam("matchType") int matchType) {

		JsonResult rs = new JsonResult();

		
		Page<Arena> page = new Page<Arena>();
		page.setPageSize(15);
		page.setPageNo(p);
		
		DetachedCriteria criteria = arenaService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchType",matchType));
		page = arenaService.findPageByCriteria(criteria, page);
		
		rs.setData(page.getResult());
		rs.setCode(JsonResult.SUCCESS);
		return rs;
	}
	
	/**
	 * 报价
	 * @param aid
	 * @return
	 */
	@RequestMapping("/json/getPrice")
	public JsonResult jsonGetPrice(@RequestParam("aid") UUID aid) {

		JsonResult rs = new JsonResult();

		
		Arena entity = arenaService.get(aid);
		
//		float[] prices =new float[3];
//		prices[0] = entity.

		ArrayList<Float> list = new ArrayList<Float>();
		list.add(entity.getPrice());
		
		String v1 = keyValueService.getByKey("judge");
		float f1 = Float.parseFloat(v1);
		
		String v2 = keyValueService.getByKey("dataRecord");
		float f2 = Float.parseFloat(v2);
		
		list.add(f1);
		list.add(f2);
		
		
		rs.setData(list);
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
	}
	
	/**
	 * 查询
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/json/search")
	public JsonResult jsonSearch(@RequestParam("key") String key) 
	{
		JsonResult rs = new JsonResult();
		DetachedCriteria criteria = arenaService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.like("name", key, MatchMode.ANYWHERE));
		
		List<Arena> list = arenaService.findByCriteria(criteria);
		if(list==null||list.size()==0)
		{
			rs.setMsg("查无此场地");
			return rs;
		}
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list);
		return rs;
		
	}

}