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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaba2.bullfight.domain.Arena;
import com.xiaba2.bullfight.domain.League;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.ArenaService;
import com.xiaba2.bullfight.service.LeagueService;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/league")
public class LeagueController {
	@Resource
	private LeagueService leagueService;
	@Resource
	private TeamService teamService;
	@Resource
	private ArenaService arenaService;

	@RequestMapping("/admin/add")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_league_add");
		
		return mv;
	}
	
	
	@RequestMapping("/admin/addfight")
	public ModelAndView addFight(@RequestParam("id") UUID id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_league_addfight");
		
		League league = leagueService.get(id);
		
		mv.addObject("league", league);
		
		return mv;
	}
	
	
	@RequestMapping("/action/add")
	public ModelAndView actionAdd(League entity, HttpServletRequest request,RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("redirect:/league/admin/add");

		String aid = request.getParameter("arenaid");
		Arena arena = arenaService.get(UUID.fromString(aid));
 
		entity.setArena(arena);
		entity.setCreatedDate(new Date());

		attr.addFlashAttribute("msg", "操作成功!");
		
		leagueService.save(entity);

		return mv;
	}
	
	@RequestMapping(value = "/action/del")
	public ModelAndView del(@RequestParam("id") UUID id,HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("redirect:/league/admin/list");
		
		League u = leagueService.get(id);
		
		u.setIsDelete(1);
		
		leagueService.saveOrUpdate(u);
		
		String rf = HttpUtil.getHeaderRef(request);
		mv.setViewName(rf);
		
		return mv;
	}
	
	
	/**
	 * 联赛列表
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList(@RequestParam("p") int p, HttpServletRequest request) {
		
		
		ModelAndView mv=new ModelAndView("admin_league_list");
		
		Page<League> page = new Page<League>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		
		DetachedCriteria criteria = leagueService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete",0));
		HttpUtil.addSearchLike(criteria, mv, request, "name");
		
		page = leagueService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml",page.genPageHtml(request));
		return mv;
	}
	
	
	/**
	 * 修改联赛状态
	 * @param id
	 * @param s
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/updatestatus")
	public ModelAndView updatestatus(@RequestParam("id") UUID id,@RequestParam("s") int s, HttpServletRequest request) {
		
		ModelAndView mv=new ModelAndView();
		
		League entity = leagueService.get(id);
		entity.setStatus(s);
		
		leagueService.saveOrUpdate(entity);
		
		mv.setViewName(HttpUtil.getHeaderRef(request));
		
		return mv;
	}
	
	
	
	/**
	 * 选择
	 * @return
	 */
	@RequestMapping(value = "/admin/sel")
	public ModelAndView pageSel() {
		
		
		ModelAndView mv=new ModelAndView("admin_league_sel");
		
		Page<League> page = new Page<League>();
		page.setPageSize(999);
		page.setPageNo(1);
		
		DetachedCriteria criteria = leagueService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete",0));
		page = leagueService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		
		return mv;
	}
	
	
	
	
	/**
	 * 筛选比赛
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/list")
	public JsonResult jsonList(@RequestParam("p") int p,
			HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		DetachedCriteria criteria = leagueService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		Page<League> page = new Page<League>();
		page.setPageSize(15);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");



		page = leagueService.findPageByCriteria(criteria, page);

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}
	

}