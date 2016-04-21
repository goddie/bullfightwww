package com.xiaba2.bullfight.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.fabric.xmlrpc.base.Array;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.MatchDataTeamService;
import com.xiaba2.bullfight.service.MatchDataUserService;
import com.xiaba2.bullfight.service.MatchFightService;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.bullfight.service.TeamUserService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/matchdatauser")
public class MatchDataUserController {
	@Resource
	private MatchDataUserService matchDataUserService;

	@Resource
	private MatchFightService matchFightService;

	@Resource
	private TeamService teamService;

	@Resource
	private UserService userService;

	@Resource
	private MatchDataTeamService matchDataTeamService;
	
	@Resource
	private TeamUserService teamUserService;

	/**
	 * 录入页面
	 * 
	 * @param mfid
	 * @param tid
	 * @return
	 */
	@RequestMapping(value = "/admin/add")
	public ModelAndView adminAdd(@RequestParam("mfid") UUID mfid,
			@RequestParam("tid") UUID tid,HttpServletRequest request) {
		if(mfid==null||tid==null)
		{
			return new ModelAndView(HttpUtil.getHeaderRef(request));
		}
		
		ModelAndView mv = new ModelAndView("admin_matchdatauser_add");

		MatchFight matchFight = matchFightService.get(mfid);
		Team team = teamService.get(tid);

		mv.addObject("matchFight", matchFight);
		mv.addObject("team", team);

		DetachedCriteria criteria = matchDataUserService
				.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		criteria.add(Restrictions.eq("team", team));
		


//		List<User> list1 = teamUserService.getUsersByTeam(team);
//		
//		List<UUID> ids = new ArrayList<UUID>();
//		for (User user : list1) {
//			ids.add(user.getId());
//		}
//		
//		criteria.add(Restrictions.in("user.id", ids));
		
		
		List<MatchDataUser> list = matchDataUserService
				.findByCriteria(criteria);
		
		
		
		
		mv.addObject("list", list);

		return mv;
	}
	
	
	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("id") UUID id,HttpServletRequest request) {
 
		ModelAndView mv = new ModelAndView();

		MatchDataUser mdu = matchDataUserService.get(id);
		
		mdu.setIsDelete(1);
 
		//保存球员单场数据
		matchDataUserService.saveOrUpdate(mdu);
		
		//更新球员个人总体数据
		userService.updateData(mdu.getUser());
		
		//更新对战队伍单场数据
		matchDataTeamService.updateMatchTeam(mdu.getMatchFight(),mdu.getTeam());
		
		//更新对战队伍总体数据
		teamService.updateData(mdu.getTeam());
		matchFightService.updateScore(mdu.getMatchFight());
		
		mv.setViewName(HttpUtil.getHeaderRef(request));
		
		return mv;
	}

	/**
	 * 录入一个球员数据
	 * 
	 * @param entity
	 * @param uid
	 * @param mfid
	 * @param tid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(MatchDataUser entity,
			@RequestParam("uid") UUID uid, @RequestParam("mfid") UUID mfid,
			@RequestParam("tid") UUID tid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(HttpUtil.getHeaderRef(request));

		if (entity.getShot() > 0) {
			entity.setGoalPercent(entity.getGoal() / entity.getShot());
		}

		if (entity.getThreeShot() > 0) {
			entity.setThreeGoalPercent(entity.getThreeGoal()
					/ entity.getThreeShot());
		}

		if (entity.getFree() > 0) {
			entity.setFreeGoalPercent(entity.getFreeGoal() / entity.getFree());
		}

		Team team = teamService.get(tid);
		entity.setTeam(team);
		
		MatchFight matchFight = matchFightService.get(mfid);
		entity.setMatchFight(matchFight);

		User user = userService.get(uid);
		entity.setUser(user);
		
		entity.setCreatedDate(new Date());
		
//		DetachedCriteria criteria = matchDataUserService.createDetachedCriteria();
//		criteria.add(Restrictions.eq("isDelete", 0));
//		criteria.add(Restrictions.eq("matchFight", entity.getMatchFight()));
//		criteria.add(Restrictions.eq("user", entity.getUser()));
//		criteria.add(Restrictions.eq("team", entity.getTeam()));
//
//		List<MatchDataUser> list = matchDataUserService.findByCriteria(criteria);
//
//		if (list != null && list.size() > 0) {
//			matchDataUserService.deleteAndUpdate(list.get(0));
//		}
		
//		matchDataUserService.saveUserData(entity);
 
//		recount(mfid);
		
		
		
		
		//保存球员单场数据
		matchDataUserService.saveOrUpdate(entity);
		
		//更新球员个人总体数据
		userService.updateData(entity.getUser());
		
		//更新对战队伍单场数据
		matchDataTeamService.updateMatchTeam(entity.getMatchFight(),entity.getTeam());
		
		//更新对战队伍总体数据
		teamService.updateData(entity.getTeam());
		
		//更新对战比分
		matchFightService.updateScore(entity.getMatchFight());

//		List<MatchDataUser> list = matchDataUserService
//				.findByCriteria(criteria);
//		
//		if (list != null && list.size() > 0) {
//			matchDataUserService.delete(list.get(0));
//		}
//
//		matchDataUserService.save(entity);
//		
//		matchDataTeamService.updateTeamByUser(entity, 1);
//
////		
//		if(team.getId().equals(matchFight.getHost().getId()))
//		{
//			matchFight.setHostScore(entity.getScoring());
//		}
//		
//		if(team.getId().equals(matchFight.getGuest().getId()))
//		{
//			matchFight.setGuestScore(entity.getScoring());
//		}
//		
//		matchFightService.saveOrUpdate(matchFight);
		
		
		return mv;
	}
	
	
	/**
	 * 玩家数据
	 */
//	public void recount (@RequestParam("mfid") UUID mfid)
//	{
//		MatchFight matchFight = matchFightService.get(mfid);
//		
//		DetachedCriteria criteria = matchDataUserService.createDetachedCriteria();
//		criteria.add(Restrictions.eq("isDelete", 0));
//		criteria.add(Restrictions.eq("matchFight", matchFight));
//		
//		List<MatchDataUser> list = matchDataUserService.findByCriteria(criteria);
//		
//		for (MatchDataUser matchDataUser : list) {
//			
//
//
//			
//
//		}
//		
//		
//	}

	/**
	 * 球员比赛
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/json/usermatch")
	public JsonResult jsonUserMatch(@RequestParam("uid") String uid,@RequestParam("p") int p,HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		if (StringUtils.isEmpty(uid)) {
			return rs;
		}

		DetachedCriteria criteria = matchDataUserService
				.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", UUID.fromString(uid)));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		
//		String statusStr =  request.getParameter("status");
//		if(!StringUtils.isEmpty(statusStr))
//		{
//			criteria.add(Restrictions.eq("matchFight.status", Integer.parseInt(statusStr)));
//		}

		Page<MatchDataUser> page = new Page<MatchDataUser>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");

		page = matchDataUserService.findPageByCriteria(criteria, page);

		List<MatchFight> list = new ArrayList<MatchFight>();
		for (MatchDataUser mdu : page.getResult()) {
			MatchFight mf = mdu.getMatchFight();
			list.add(mf);
		}

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list);

		return rs;
	}
	
	
	/**
	 * 上场球员详细数据
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/json/userdata")
	public JsonResult jsonUserData(@RequestParam("mfid") UUID mfid) {
		JsonResult rs = new JsonResult();
		
		MatchFight matchFight = matchFightService.get(mfid);
		
		Team host = matchFight.getHost();
		Team guest = matchFight.getGuest();
		
		DetachedCriteria criteria1 = matchDataUserService.createDetachedCriteria();
		criteria1.add(Restrictions.eq("isDelete",0));
		criteria1.add(Restrictions.eq("team", host));
		criteria1.add(Restrictions.eq("matchFight",matchFight));
		List<MatchDataUser> list1 = matchDataUserService.findByCriteria(criteria1);
		
		DetachedCriteria criteria2 = matchDataUserService.createDetachedCriteria();
		criteria2.add(Restrictions.eq("isDelete",0));
		criteria2.add(Restrictions.eq("team", guest));
		criteria2.add(Restrictions.eq("matchFight",matchFight));
		List<MatchDataUser> list2 = matchDataUserService.findByCriteria(criteria2);
		
		
		List<List<MatchDataUser>> listBig = new ArrayList<List<MatchDataUser>>();
		listBig.add(list1);
		listBig.add(list2);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(listBig);

		
		return rs;
	}
	
	/**
	 * 得分
	 * @param leagueid
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/leaguepoint")
	public JsonResult getLeaguePoint(@RequestParam("leagueid") UUID leagueid,@RequestParam("p") int p,HttpServletRequest request)
	{
		JsonResult rs = new JsonResult();
		
		int start = Math.max((p - 1)*15-1,0);

		String sql = "select hex(mu.user_id) userid,count(user_id) plays,sum(mu.rebound) rebound,sum(mu.assist) assist,sum(mu.scoring) scoring,"
				+ "avg(mu.rebound) avgrebound,avg(mu.assist) avgassist,avg(scoring) avgscoring"
				+ " from db_bullfight_matchdatauser mu left join db_bullfight_matchfight mf "
				+ "on mu.matchFight_id = mf.id where mf.league_id = unhex('"+leagueid.toString().replaceAll("-", "")+"') "
				+ "group by mu.user_id order by scoring desc limit "+start+",15;";
		
		List<Map<String, Object>> sqlrs = matchDataTeamService.findByNativeSQL(sql);
		List<DataUser> list = new ArrayList<DataUser>();
		DecimalFormat df=new DecimalFormat(".0");
 
		
		for (Map<String, Object> map : sqlrs) {
			
			DataUser du = new DataUser();
			
			du.setAssist(Float.parseFloat(df.format(map.get("assist"))));
			du.setAvgassist(Float.parseFloat(df.format(map.get("avgassist"))));
			du.setRebound(Float.parseFloat(df.format(map.get("rebound"))));
			du.setAvgrebound(Float.parseFloat(df.format(map.get("avgrebound"))));
			du.setPlays(Float.parseFloat(df.format(map.get("plays"))));
			du.setScoring(Float.parseFloat(df.format(map.get("scoring"))));
			du.setAvgscoring(Float.parseFloat(df.format(map.get("avgscoring"))));

			UUID uuid = HttpUtil.uuidMysqlString(map.get("userid").toString());
			du.setUser(userService.get(uuid));
			
			list.add(du);
		}
		
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list);
		
		return rs;
	}
	
	/**
	 * 篮板
	 * @param leagueid
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/leaguerebound")
	public JsonResult getLeagueRebound(@RequestParam("leagueid") UUID leagueid,@RequestParam("p") int p,HttpServletRequest request)
	{
		JsonResult rs = new JsonResult();
		
		int start = Math.max((p - 1)*15-1,0);

		String sql = "select hex(mu.user_id) userid,count(user_id) plays,sum(mu.rebound) rebound,sum(mu.assist) assist,sum(mu.scoring) scoring,"
				+ "avg(mu.rebound) avgrebound,avg(mu.assist) avgassist,avg(scoring) avgscoring"
				+ " from db_bullfight_matchdatauser mu left join db_bullfight_matchfight mf "
				+ "on mu.matchFight_id = mf.id where mf.league_id = unhex('"+leagueid.toString().replaceAll("-", "")+"') "
				+ "group by mu.user_id order by rebound desc limit "+start+",15;";
		
		List<Map<String, Object>> sqlrs = matchDataTeamService.findByNativeSQL(sql);
		List<DataUser> list = new ArrayList<DataUser>();
		DecimalFormat df=new DecimalFormat(".0");
		for (Map<String, Object> map : sqlrs) {
			
			DataUser du = new DataUser();
			
			du.setAssist(Float.parseFloat(df.format(map.get("assist"))));
			du.setAvgassist(Float.parseFloat(df.format(map.get("avgassist"))));
			du.setRebound(Float.parseFloat(df.format(map.get("rebound"))));
			du.setAvgrebound(Float.parseFloat(df.format(map.get("avgrebound"))));
			du.setPlays(Float.parseFloat(df.format(map.get("plays"))));
			du.setScoring(Float.parseFloat(df.format(map.get("scoring"))));
			du.setAvgscoring(Float.parseFloat(df.format(map.get("avgscoring"))));

			UUID uuid = HttpUtil.uuidMysqlString(map.get("userid").toString());
			du.setUser(userService.get(uuid));
			
			list.add(du);
		}
		
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list);
		
		
		return rs;
	}
	
	
	@RequestMapping(value = "/json/leagueassist")
	public JsonResult getLeagueAssist(@RequestParam("leagueid") UUID leagueid,@RequestParam("p") int p,HttpServletRequest request)
	{
		JsonResult rs = new JsonResult();
		
		
		int start = Math.max((p - 1)*15-1,0);

		String sql = "select hex(mu.user_id) userid,count(user_id) plays,sum(mu.rebound) rebound,sum(mu.assist) assist,sum(mu.scoring) scoring,"
				+ "avg(mu.rebound) avgrebound,avg(mu.assist) avgassist,avg(scoring) avgscoring"
				+ " from db_bullfight_matchdatauser mu left join db_bullfight_matchfight mf "
				+ "on mu.matchFight_id = mf.id where mf.league_id = unhex('"+leagueid.toString().replaceAll("-", "")+"') "
				+ "group by mu.user_id order by assist desc limit "+start+",15;";
		
		List<Map<String, Object>> sqlrs = matchDataTeamService.findByNativeSQL(sql);
		List<DataUser> list = new ArrayList<DataUser>();
		DecimalFormat df=new DecimalFormat(".0");
		for (Map<String, Object> map : sqlrs) {
			
			DataUser du = new DataUser();
			
			du.setAssist(Float.parseFloat(df.format(map.get("assist"))));
			du.setAvgassist(Float.parseFloat(df.format(map.get("avgassist"))));
			du.setRebound(Float.parseFloat(df.format(map.get("rebound"))));
			du.setAvgrebound(Float.parseFloat(df.format(map.get("avgrebound"))));
			du.setPlays(Float.parseFloat(df.format(map.get("plays"))));
			du.setScoring(Float.parseFloat(df.format(map.get("scoring"))));
			du.setAvgscoring(Float.parseFloat(df.format(map.get("avgscoring"))));

			UUID uuid = HttpUtil.uuidMysqlString(map.get("userid").toString());
			du.setUser(userService.get(uuid));
			
			list.add(du);
		}
		
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list);
		
		return rs;
	}
	
	
	/**
	 * 玩家数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/count")
	public ModelAndView countUserData(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		DetachedCriteria criteria1 = userService.createDetachedCriteria();
		criteria1.add(Restrictions.eq("isDelete", 0));
//		criteria1.add(Restrictions.eq("id",UUID.fromString("f644c4e6-6ae9-44e3-9faf-9ba3a3ebfbf6")));

		List<User> list1 = userService.findByCriteria(criteria1);
		
		for (User user : list1) 
		{
//			DetachedCriteria criteria = matchFightService.createDetachedCriteria();
//			criteria.add(Restrictions.eq("isDelete", 0));
//			criteria.add(Restrictions.eq("user", user));
// 
//			List<MatchDataUser> list = matchDataUserService.findByCriteria(criteria);
//
//			for (MatchDataUser matchDataUser : list) {
//
//
//				
//				
//			}
			
//			String sqlwin = "select count(mu.id) win from db_bullfight_matchdatauser mu left join db_bullfight_matchfight mf "
//					+ "on mu.matchfight_id = mf.id where user_id = unhex('"+user.getId().toString().replaceAll("-", "")+"') "
//					+ "and mf.status=2 and mf.winner_id = mu.team_id";
//			
//			String sqllose = "select count(mu.id) lose from db_bullfight_matchdatauser mu left join db_bullfight_matchfight mf "
//					+ "on mu.matchfight_id = mf.id where user_id = unhex('"+user.getId().toString().replaceAll("-", "")+"') "
//					+ "and mf.status=2 and mf.loser_id = mu.team_id";
//			
//			Map<String,Object> mapwin = matchDataUserService.findByNativeSQL(sqlwin).get(0);
//			float win = HttpUtil.toFloat(mapwin.get("win"));
//			
//			Map<String,Object> maplose = matchDataUserService.findByNativeSQL(sqllose).get(0);
//			float lose = HttpUtil.toFloat(maplose.get("lose"));
//			
//			
//			user.setWin(win);
//			user.setLose(lose);
//			user.setPlayCount(win+lose);
			
			userService.updateData(user);

		}

		mv.setViewName(HttpUtil.getHeaderRef(request));
		return mv;
	}
	
	
	
	public class DataUser
	{
	    private float rebound;
	    private float assist;
	    private float scoring;
	    private float plays;
	    private float avgrebound;
	    private float avgassist;
	    private float avgscoring;
	    private User user;
		public float getRebound() {
			return rebound;
		}
		public void setRebound(float rebound) {
			this.rebound = rebound;
		}
		public float getAssist() {
			return assist;
		}
		public void setAssist(float assist) {
			this.assist = assist;
		}
		public float getScoring() {
			return scoring;
		}
		public void setScoring(float scoring) {
			this.scoring = scoring;
		}
		public float getPlays() {
			return plays;
		}
		public void setPlays(float plays) {
			this.plays = plays;
		}
		public float getAvgrebound() {
			return avgrebound;
		}
		public void setAvgrebound(float avgrebound) {
			this.avgrebound = avgrebound;
		}
		public float getAvgassist() {
			return avgassist;
		}
		public void setAvgassist(float avgassist) {
			this.avgassist = avgassist;
		}
		public float getAvgscoring() {
			return avgscoring;
		}
		public void setAvgscoring(float avgscoring) {
			this.avgscoring = avgscoring;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		 
	    
	}

	
}

