package com.xiaba2.bullfight.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.Arena;
import com.xiaba2.bullfight.domain.League;
import com.xiaba2.bullfight.domain.MatchDataTeam;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.MatchFightUser;
import com.xiaba2.bullfight.domain.PayRecord;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.domain.TeamUser;
import com.xiaba2.bullfight.service.ArenaService;
import com.xiaba2.bullfight.service.KeyValueService;
import com.xiaba2.bullfight.service.LeagueRecordService;
import com.xiaba2.bullfight.service.LeagueService;
import com.xiaba2.bullfight.service.MatchDataTeamService;
import com.xiaba2.bullfight.service.MatchDataUserService;
import com.xiaba2.bullfight.service.MatchFightService;
import com.xiaba2.bullfight.service.MatchFightUserService;
import com.xiaba2.bullfight.service.PayRecordService;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/matchfight")
public class MatchFightController {
	@Resource
	private MatchFightService matchFightService;

	@Resource
	private TeamService teamService;

	@Resource
	private ArenaService arenaService;

	@Resource
	private UserService userService;

	@Resource
	private KeyValueService keyValueService;

	@Resource
	private PayRecordService payRecordService;

	@Resource
	private MatchDataTeamService matchDataTeamService;
	
	@Resource
	private MatchDataUserService matchDataUserService;
	
	@Resource
	private MatchFightUserService  matchFightUserService ;
	@Resource
	private LeagueService leagueService;
	
	@Resource
	private LeagueRecordService leagueRecordService;

	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_matchfight_" + name);
	}

	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList(@RequestParam("p") int p,
			HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("admin_matchfight_list");

		Page<MatchFight> page = new Page<MatchFight>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = matchFightService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.not(Restrictions.eq("matchType", 3)));
		
		String name=request.getParameter("name");
		if(!StringUtils.isEmpty(name))
		{
 
			DetachedCriteria criteria2= teamService.createDetachedCriteria();
			criteria2.add(Restrictions.eq("name", name));
			List<Team> list2= teamService.findByCriteria(criteria2);
			if(list2!=null&&list2.size()>0)
			{
				criteria.add(Restrictions.or( Restrictions.eq("host", list2.get(0)),Restrictions.eq("guest", list2.get(0))));
			}
		}
		
		page = matchFightService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml", page.genPageHtml(request));

		return mv;
	}
	
	
	@RequestMapping(value = "/admin/teamlist")
	public ModelAndView adminTeamList(@RequestParam("p") int p,@RequestParam("teamid") UUID teamid,
			HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("admin_matchfight_teamlist");

		Team team = teamService.get(teamid);
		
		Page<MatchFight> page = new Page<MatchFight>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = matchFightService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.or(Restrictions.eq("host", team),Restrictions.eq("guest", team)));

		page = matchFightService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml", page.genPageHtml(request));

		return mv;
	}
	
	/**
	 * 联赛比赛列表
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/leaguelist")
	public ModelAndView pageLeagueList(@RequestParam("id") UUID leagueid, @RequestParam("p") int p,
			HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("admin_league_fightlist");
		
		League league = leagueService.get(leagueid);

		Page<MatchFight> page = new Page<MatchFight>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = matchFightService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchType", 3));
		criteria.add(Restrictions.eq("league", league));
		
		page = matchFightService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml", page.genPageHtml(request));

		return mv;
	}

	/**
	 *  直接支付
	 * @param mfid
	 * @return
	 */
	@RequestMapping("/action/pay")
	public ModelAndView actionPay(@RequestParam("mfid") UUID mfid) {
		ModelAndView mv = new ModelAndView("redirect:/matchfight/admin/list?p=1");

		MatchFight entity = matchFightService.get(mfid);
		entity.setIsPay(1);

		matchFightService.saveOrUpdate(entity);

		return mv;
	}

	@RequestMapping("/action/del")
	public ModelAndView actionDel(@RequestParam("mfid") UUID mfid,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(HttpUtil.getHeaderRef(request));

		MatchFight entity = matchFightService.get(mfid);
		entity.setIsDelete(1);
		
		
		matchDataTeamService.deleteByMatchFight(entity);
		matchDataUserService.deleteByMatchFight(entity);
		
		matchFightService.saveOrUpdate(entity);
		
		
		
		//更新队伍数据
		DetachedCriteria criteria1 = teamService.createDetachedCriteria();
		criteria1.add(Restrictions.eq("isDelete", 0));
//		criteria1.add(Restrictions.eq("id",UUID.fromString("f644c4e6-6ae9-44e3-9faf-9ba3a3ebfbf6")));

		List<Team> list1 = teamService.findByCriteria(criteria1);
		for (Team team : list1) {
			DetachedCriteria criteria = matchFightService.createDetachedCriteria();
			criteria.add(Restrictions.eq("isDelete", 0));
			criteria.add(Restrictions.eq("status", 2));
			criteria.add(Restrictions.or(Restrictions.eq("host", team), Restrictions.eq("guest", team)));

			List<MatchFight> list = matchFightService.findByCriteria(criteria);

			for (MatchFight matchFight : list) {

				// 更新对战队伍单场数据
				matchDataTeamService.updateMatchTeam(matchFight, team);

				// 更新对战队伍总体数据
				teamService.updateData(team);
				
				teamService.countWinLose(team);
				
				matchFightService.updateScore(matchFight);
				
			}

		}
		
		
		
		//更新个人数据
		DetachedCriteria criteria2 = userService.createDetachedCriteria();
		criteria1.add(Restrictions.eq("isDelete", 0));
		List<User> list2 = userService.findByCriteria(criteria2);
		
		for (User user : list2) 
		{
			userService.updateData(user);
		}
		
		

		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(MatchFight entity, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_matchfight_add");

		String hostTeamId = request.getParameter("hostteam");
		String guestTeamId = request.getParameter("guestteam");
		String arenaId = request.getParameter("arenaid");

		if (StringUtils.isEmpty(hostTeamId) || StringUtils.isEmpty(arenaId)) {
			return mv;
		}

		Team host = teamService.get(UUID.fromString(hostTeamId));

		if (!StringUtils.isEmpty(guestTeamId)) {
			Team guest = teamService.get(UUID.fromString(guestTeamId));
			entity.setGuest(guest);
			entity.setStatus(1);
		}

		Arena arena = arenaService.get(UUID.fromString(arenaId));

		entity.setArena(arena);
		entity.setHost(host);

		entity.setCreatedDate(new Date());
		entity.setStatus(1);
//		entity.setIsPay(1);
		//后台创建不再支付
		entity.setIsPay(2);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

		Date dstart;
		Date dend;

		try {

			dstart = sdf.parse(request.getParameter("startDateStr"));
			entity.setStart(dstart);

			dend = sdf.parse(request.getParameter("endDateStr"));
			entity.setEnd(dend);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//联赛
		if(entity.getMatchType()==3)
		{
			String leagueid = request.getParameter("leagueid");
			if(!StringUtils.isEmpty(leagueid))
			{
				League league = leagueService.get(UUID.fromString(leagueid));
				entity.setLeague(league);
			}
			
		}

		matchFightService.save(entity);

		String s = HttpUtil.getHeaderRef(request);
		mv.setViewName(s);
		
		return mv;
	}

	/**
	 * 创建
	 * 
	 * @param entity
	 * @param tid
	 * @param aid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/add")
	public JsonResult jsonAdd(MatchFight entity,
			@RequestParam("aid") String aid,@RequestParam("uid") UUID uid, HttpServletRequest request) {
		JsonResult js = new JsonResult();
		String tid = request.getParameter("tid");
		Arena arena = arenaService.get(UUID.fromString(aid));
		if (arena == null) {
			return js;
		}
		if (!StringUtils.isEmpty(tid)) {
			Team team = teamService.get(UUID.fromString(tid));
			if (team != null) {
				entity.setHost(team);
			}

		}

		entity.setArena(arena);
		entity.setCreatedDate(new Date());

		//entity.setStatus(0);
		if(entity.getFee()>0)
		{
			entity.setIsPay(0);
		}
		

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date dstart;
		Date dend;

		try {

			dstart = sdf.parse(request.getParameter("startStr"));
			entity.setStart(dstart);

			dend = sdf.parse(request.getParameter("endStr"));
			entity.setEnd(dend);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		matchFightService.save(entity);

		// 团队约战要收钱
		if (entity.getMatchType() == 1 && entity.getIsPay()==0) {
			createPayRecord(entity);
		}

		// 野球娱乐自动加入
		if (entity.getMatchType() == 2) {
			User user = userService.get(uid);
			MatchFightUser matchFightUser = new MatchFightUser();
			matchFightUser.setUser(user);
			matchFightUser.setMatchFight(entity);
			//matchFightUser.setTeam(entity.getHost());
			matchFightUser.setCreatedDate(new Date());
			
			matchFightUserService.save(matchFightUser);
		}

		js.setData(entity);
		js.setCode(JsonResult.SUCCESS);
		js.setMsg("创建成功");

		return js;
	}

 

	/**
	 * 创建2个待支付记录
	 * 
	 * @param matchFight
	 */
	void createPayRecord(MatchFight matchFight) {
		// 相差小时数
		double hour = (matchFight.getEnd().getTime() - matchFight.getStart()
				.getTime()) / (1000 * 60 * 60);

		BigDecimal b = new BigDecimal(String.valueOf(hour));
		double d = b.doubleValue();

		int countHour = (int) Math.ceil(d);

		float arenaPay = matchFight.getArena().getPrice() * countHour * 0.5f;

		String judgeFee = keyValueService.getByKey("judge");
		float judgePay = Float.parseFloat(judgeFee) * matchFight.getJudge()
				* 0.5f;

		String recordFee = keyValueService.getByKey("dataRecord");
		float recordPay = Float.parseFloat(recordFee)
				* matchFight.getDataRecord() * 0.5f;

		PayRecord payRecord = new PayRecord();
		payRecord.setArenaFee(arenaPay);
		payRecord.setJudgeFee(judgePay);
		payRecord.setDataRecordFee(recordPay);
		payRecord.setTotal(arenaPay + judgePay + recordPay);
		payRecord.setMatchFight(matchFight);
		payRecord.setUser(matchFight.getHost().getAdmin());
		payRecord.setCreatedDate(new Date());
		payRecord.setStatus(0);

		payRecordService.save(payRecord);

		PayRecord payRecord2 = new PayRecord();
		payRecord2.setArenaFee(arenaPay);
		payRecord2.setJudgeFee(judgePay);
		payRecord2.setDataRecordFee(recordPay);
		payRecord2.setTotal(arenaPay + judgePay + recordPay);
		payRecord2.setMatchFight(matchFight);
		// payRecord2.setUser(matchFight.getHost().getAdmin());
		payRecord2.setCreatedDate(new Date());
		payRecord2.setStatus(0);

		payRecordService.save(payRecord2);
	}

	/**
	 * 筛选比赛
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/matchlist")
	public JsonResult jsonMatchList(@RequestParam("p") int p,
			HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		DetachedCriteria criteria = matchFightService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		Page<MatchFight> page = new Page<MatchFight>();
		page.setPageSize(15);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		// 比赛类型
		int matchType = 1;
		String mt = request.getParameter("matchType");
		if (!StringUtils.isEmpty(mt)) {
			matchType = Integer.parseInt(mt);
			criteria.add(Restrictions.eq("matchType", matchType));

			if (matchType == 1) {
				//1 已支付  2后台创建
				criteria.add(Restrictions.gt("isPay", 0));
			}
			
			//联赛
			if( matchType==3)
			{
				String leaguidstr = request.getParameter("leagueid");
				if(!StringUtils.isEmpty(leaguidstr))
				{
					UUID leaguid = UUID.fromString(leaguidstr);
					criteria.add(Restrictions.eq("league.id", leaguid));
					
				}
				
			}

		}

		// 比赛状态
		int status = -1;
		String st = request.getParameter("status");
		if (!StringUtils.isEmpty(st)) {
			status = Integer.parseInt(st);
			if (status != -1) {
				criteria.add(Restrictions.eq("status", status));
			}

		}

		// 城市
		String city = request.getParameter("city");
		if (!StringUtils.isEmpty(city)) {
			criteria.add(Restrictions.eq("city", city));
		}

		// String pg = request.getParameter("page");
		// if(!StringUtils.isEmpty(pg))
		// {
		// int p = Integer.parseInt(pg);
		// page.setPageNo(p);
		// }

		page = matchFightService.findPageByCriteria(criteria, page);

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}

	/**
	 * 
	 * 结束比赛
	 * 
	 * @param mfid
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/finish")
	public JsonResult jsonFinish(@RequestParam("mfid") UUID mfid, HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		MatchFight matchFight = matchFightService.get(mfid);

		Team host = matchFight.getHost();
		Team guest = matchFight.getGuest();

		if (host == null || guest == null) {
			rs.setMsg("比赛双方不齐");
			return rs;
		}
		
		
		matchFight.setStatus(2);
		matchFightService.saveOrUpdate(matchFight);
		
		
		
		// 更新对战队伍单场数据
		matchDataTeamService.updateMatchTeam(matchFight, host);
		// 更新对战队伍总体数据
		teamService.updateData(host);
		teamService.countWinLose(host);
		
		// 更新对战队伍单场数据
		matchDataTeamService.updateMatchTeam(matchFight, guest);
		// 更新对战队伍总体数据
		teamService.updateData(guest);
		teamService.countWinLose(guest);
		
		
		matchFightService.updateScore(matchFight);
		
		
		
		
//		
//		teamService.countWinLose(host);
//		teamService.countWinLose(guest);
//		
//		teamService.saveOrUpdate(host);
//		teamService.saveOrUpdate(guest);
//		
//		if(matchFight.getHostScore()>matchFight.getGuestScore())
//		{
//			matchFight.setWinner(host);
//			matchFight.setLoser(guest);
//			
//		}
//
//		if(matchFight.getHostScore()<matchFight.getGuestScore())
//		{
//			matchFight.setWinner(guest);
//			matchFight.setLoser(host);
//		}
//		
//		matchFightService.saveOrUpdate(matchFight);

		
		//联赛积分
		if(matchFight.getLeague()!=null)
		{
			leagueRecordService.countLeagueRecord(matchFight.getLeague());
		}

		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("操作成功");
		return rs;

		// if (matchFight.getHostScore() == 0
		// || matchFight.getGuestScore() == 0) {
		// rs.setMsg("比赛双方比分不正确");
		// return rs;
		// }

//		MatchDataTeam hostData = matchDataTeamService.getByMatchFight(
//				matchFight, host);
//		matchFight.setHostScore(hostData.getScoring());
//
//		MatchDataTeam guestData = matchDataTeamService.getByMatchFight(
//				matchFight, guest);
//		matchFight.setGuestScore(guestData.getScoring());
//
//		if (matchFight.getHostScore() > matchFight.getGuestScore()) {
//			matchFight.setWinner(matchFight.getHost());
//
//			host.setWin(host.getWin() + 1);
//			guest.setLose(guest.getLose() + 1);
//
//		}
//
//		if (matchFight.getHostScore() < matchFight.getGuestScore()) {
//			matchFight.setWinner(matchFight.getGuest());
//
//			guest.setWin(guest.getWin() + 1);
//			host.setLose(host.getLose() + 1);
//		}
//
//		if (matchFight.getHostScore() == matchFight.getGuestScore()) {
//			matchFight.setDraw(1);
//			guest.setDraw(guest.getDraw() + 1);
//			host.setDraw(host.getDraw() + 1);
//
//		}

		// host.setPlayCount(host.getPlayCount()+1);
		// guest.setPlayCount(guest.getPlayCount()+1);
		
		
		

//		//比赛场次
//		String sql1 = "select count(id) played from db_bullfight_matchfight"
//				+ " where isdelete=0 and ( host_id = unhex('"+host.getId().toString().replaceAll("-", "")+"') "
//			    + " or guest_id = unhex('"+host.getId().toString().replaceAll("-", "")+"') )";
//		
//		Map<String,Object> map1 = teamService.findByNativeSQL(sql1).get(0);
//		host.setPlayCount(HttpUtil.toFloat(map1.get("played")));
//		
//		
//		String sql2 = "select count(id) played from db_bullfight_matchfight"
//				+ " where isdelete=0 and ( host_id = unhex('"+guest.getId().toString().replaceAll("-", "")+"') "
//			    + " or guest_id = unhex('"+guest.getId().toString().replaceAll("-", "")+"') )";
//		Map<String,Object> map2 = teamService.findByNativeSQL(sql2).get(0);
//		guest.setPlayCount(HttpUtil.toFloat(map2.get("played")));
		
		
	}

	/**
	 * 
	 * 应战
	 * 
	 * @param mfid
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/accept")
	public JsonResult jsonAccept(@RequestParam("mfid") String mfid,
			@RequestParam("uid") String uid, HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		MatchFight matchFight = matchFightService.get(UUID.fromString(mfid));
		User user = userService.get(UUID.fromString(uid));

		if (matchFight == null || user == null) {
			return rs;
		}
		
		
		
		String teamid = request.getParameter("tid");
		if(StringUtils.isEmpty(teamid))
		{
			DetachedCriteria criteria = teamService.createDetachedCriteria();
			criteria.add(Restrictions.eq("admin.id", user.getId()));
			criteria.add(Restrictions.eq("isDelete", 0));
			List<Team> list = teamService.findByCriteria(criteria);

			if (list.isEmpty()) {
				rs.setMsg("你不是队长");
				return rs;
			}
			
			matchFight.setGuest(list.get(0));
			matchFight.setStatus(1);
			matchFight.setLastModifiedDate(new Date());
			
			matchFightService.saveOrUpdate(matchFight);
		}else
		{
			Team team  = teamService.get(UUID.fromString(teamid));
			matchFight.setGuest(team);
			matchFight.setStatus(1);
			matchFight.setLastModifiedDate(new Date());
			
			matchFightService.saveOrUpdate(matchFight);
		}
		

		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("操作成功");

		return rs;
	}

}