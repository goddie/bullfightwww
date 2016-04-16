package com.xiaba2.bullfight.controller;

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

import com.xiaba2.bullfight.domain.League;
import com.xiaba2.bullfight.domain.LeagueRecord;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.LeagueRecordService;
import com.xiaba2.bullfight.service.LeagueService;
import com.xiaba2.bullfight.service.MatchFightService;
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
	
	@Resource
	private MatchFightService matchFightService;
	
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
		page.addOrder("pointSum", "desc");



		page = leagueRecordService.findPageByCriteria(criteria, page);

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}
	
	@RequestMapping("/json/count")
	public JsonResult jsonCount() {
		JsonResult rs = new JsonResult();

		List<League> list = leagueService.loadAll();
		
		for (League league : list) {
			countRecord(league.getId());
		}

		rs.setMsg("ok");
		
		return rs;
	}
 
	
	
	
	
	/**
	 * 统计积分
	 * @param leagueid
	 */
	public void countRecord(UUID leagueid) {
		JsonResult rs = new JsonResult();

		League league = leagueService.get(leagueid);
		
		
		DetachedCriteria criteria = matchFightService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("league.id", leagueid));
		
		
		
		List<MatchFight> list = matchFightService.findByCriteria(criteria);
		for (MatchFight matchFight : list) {
		
			LeagueRecord host = fingEntity(matchFight.getHost(),league);
			LeagueRecord guest = fingEntity(matchFight.getGuest(),league);
			
			
			//胜
			if(matchFight.getHostScore()>matchFight.getGuestScore())
			{
				host.setWin(host.getWin()+1);
				host.setScore(host.getScore()+3);
				host.setPointSum(host.getPointSum()+ (int)matchFight.getHostScore());
				host.setPlays(host.getPlays()+1);
				host.setPointAvg(host.getPointSum()/host.getPlays());
				
				
				guest.setLose(guest.getLose()+1);
				guest.setScore(guest.getScore()+1);
				guest.setPointSum(guest.getPointSum()+ (int)matchFight.getGuestScore());
				guest.setPlays(guest.getPlays()+1);
				guest.setPointAvg(guest.getPointSum()/guest.getPlays());
			}
			
			//负
			if(matchFight.getHostScore()<matchFight.getGuestScore())
			{
				
				host.setLose(host.getLose()+1);
				host.setScore(host.getScore()+1);
				host.setPointSum(host.getPointSum()+ (int)matchFight.getHostScore());
				host.setPlays(host.getPlays()+1);
				host.setPointAvg(host.getPointSum()/host.getPlays());
				
				
				guest.setWin(guest.getWin()+1);
				guest.setScore(guest.getScore()+3);
				guest.setPointSum(guest.getPointSum()+ (int)matchFight.getGuestScore());
				guest.setPlays(guest.getPlays()+1);
				guest.setPointAvg(guest.getPointSum()/guest.getPlays());
			}
 
			
			
			leagueRecordService.saveOrUpdate(host);
			leagueRecordService.saveOrUpdate(guest);
				
		}
	}
	
	
	private LeagueRecord fingEntity(Team team,League league)
	{
		DetachedCriteria criteria = leagueRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("league.id", league.getId()));
		criteria.add(Restrictions.eq("team.id", team.getId()));

		List<LeagueRecord> list = leagueRecordService.findByCriteria(criteria);
		if(list==null||list.size()==0)
		{
			LeagueRecord leagueRecord = new LeagueRecord();
			leagueRecord.setTeam(team);
			leagueRecord.setLeague(league);
			leagueRecord.setCreatedDate(new Date());
			
			leagueRecordService.save(leagueRecord);
			
			return leagueRecord;
		}else
		{
			return list.get(0);
		}
		
	}
	
}