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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaba2.bullfight.domain.Arena;
import com.xiaba2.bullfight.domain.League;
import com.xiaba2.bullfight.domain.LeagueTeam;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.LeagueService;
import com.xiaba2.bullfight.service.LeagueTeamService;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/leagueteam")
public class LeagueTeamController {
	@Resource
	private LeagueTeamService leagueTeamService;
	
	@Resource
	private TeamService teamService;
	
	@Resource
	private LeagueService leagueService;
	
	
	@RequestMapping("/admin/add")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_leagueteam_add");
		
		return mv;
	}
	
	
	@RequestMapping(value = "/action/del")
	public ModelAndView del(@RequestParam("id") UUID id,HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("redirect:/league/admin/list");
		
		LeagueTeam u = leagueTeamService.get(id);
		
		u.setIsDelete(1);
		
		leagueTeamService.saveOrUpdate(u);
		
		String rf = HttpUtil.getHeaderRef(request);
		mv.setViewName(rf);
		
		return mv;
	}

	@RequestMapping("/action/add")
	public ModelAndView actionAdd(LeagueTeam entity, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("redirect:/leagueteam/admin/add");

		String teamid = request.getParameter("teamid");
		Team team = teamService.get(UUID.fromString(teamid));
		
		String leagueid = request.getParameter("leagueid");
		League league = leagueService.get(UUID.fromString(leagueid));

		entity.setTeam(team);
		entity.setLeague(league);
		
		entity.setCreatedDate(new Date());

		attr.addFlashAttribute("msg", "操作成功!");

		leagueTeamService.save(entity);

		return mv;
	}
	
	
	/**
	 * 队伍列表
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList(@RequestParam("p") int p, HttpServletRequest request) {
		
		
		ModelAndView mv=new ModelAndView("admin_leagueteam_list");
		
		Page<LeagueTeam> page = new Page<LeagueTeam>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		
		DetachedCriteria criteria = leagueTeamService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete",0));
		criteria.add(Restrictions.not(Restrictions.eq("isPay",2)));
		HttpUtil.addSearchLike(criteria, mv, request, "name");
		
		page = leagueTeamService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml",page.genPageHtml(request));
		return mv;
	}
	
	
	/**
	 * 联赛队伍选择
	 * @return
	 */
	@RequestMapping(value = "/admin/leaguesel")
	public ModelAndView pageLeagueSel(@RequestParam("id") UUID leagueid) {
		
		
		ModelAndView mv=new ModelAndView("admin_team_sel");
		
		Page<LeagueTeam> page = new Page<LeagueTeam>();
		page.setPageSize(999);
		page.setPageNo(1);
		
		DetachedCriteria criteria = leagueTeamService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete",0));
		criteria.add(Restrictions.eq("league.id",leagueid));
		
		page = leagueTeamService.findPageByCriteria(criteria, page);
		
		ArrayList<Team> teams = new ArrayList<Team>();
		for (LeagueTeam leagueTeam : page.getResult()) {
			teams.add(leagueTeam.getTeam());
		}
		
		mv.addObject("list", teams);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(@RequestParam("leagueid") UUID leagueid,@RequestParam("teamid") UUID teamid,HttpServletRequest request) {

		JsonResult rs = new JsonResult();

		LeagueTeam entity = new LeagueTeam();
		
		Team team = teamService.get(teamid);
		League league = leagueService.get(leagueid);
		
		entity.setTeam(team);
		entity.setLeague(league);
		entity.setCreatedDate(new Date());
		
		entity.setContact(request.getParameter("contact"));
		entity.setPhone(request.getParameter("phone"));
		entity.setPay(Float.parseFloat(request.getParameter("pay")));
		
		entity.setIsPay(0);
		
		
		leagueTeamService.save(entity);
		
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
	}

}