package com.xiaba2.bullfight.controller;

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

import com.xiaba2.bullfight.domain.League;
import com.xiaba2.bullfight.domain.LeagueRecord;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.LeagueRecordService;
import com.xiaba2.bullfight.service.LeagueService;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/leaguerecord")
public class LeagueRecordController {
	@Resource
	private LeagueRecordService leagueRecordService;
	
	@Resource
	private LeagueService leagueService;
	
	@Resource
	private TeamService teamService;
	
	/**
	 * 新增积分
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/add")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_leaguerecord_add");
		
		return mv;
	}
	
	
	/**
	 * 修改积分
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/update")
	public ModelAndView update(@RequestParam("teamid") UUID teamid,@RequestParam("leagueid") UUID leagueid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_leaguerecord_update");
		
		DetachedCriteria criteria = leagueRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("league.id", leagueid));
		criteria.add(Restrictions.eq("team.id", teamid));
		
		List<LeagueRecord> list = leagueRecordService.findByCriteria(criteria);
		
		LeagueRecord entity = new LeagueRecord();
		
		
		if(list==null||list.size()==0)
		{
			
			
			Team team = teamService.get(teamid);
			League league = leagueService.get(leagueid);
			
			entity.setTeam(team);
			entity.setLeague(league);
			
			leagueRecordService.save(entity);
		}else
		{
			entity = list.get(0);
		}
		
		mv.addObject("entity", entity);
		mv.addObject("league", entity.getLeague());
		mv.addObject("team", entity.getTeam());
		return mv;
	}
	
	
	@RequestMapping("/action/update")
	public ModelAndView actionUpdate(@RequestParam("id") UUID id,LeagueRecord entity,HttpServletRequest request,RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("redirect:/leaguerecord/admin/update");
		
		LeagueRecord leagueRecord=leagueRecordService.get(id);
		leagueRecord.setWin(entity.getWin());
		leagueRecord.setLose(entity.getLose());
		leagueRecord.setScore(entity.getScore());
		
		leagueRecordService.saveOrUpdate(leagueRecord);
		
		mv.setViewName(HttpUtil.getHeaderRef(request));
		attr.addFlashAttribute("msg","修改成功!");
		
		//mv.setView(HttpUtil.getHeaderRef(request));
		return mv;
	}
	
	/**
	 * 记录表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/list")
	public JsonResult jsonList(@RequestParam("p") int p,@RequestParam("leagueid") UUID leagueid,
			HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		DetachedCriteria criteria = leagueRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("league.id", leagueid));

		Page<LeagueRecord> page = new Page<LeagueRecord>();
		page.setPageSize(15);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");



		page = leagueRecordService.findPageByCriteria(criteria, page);

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}
	
}