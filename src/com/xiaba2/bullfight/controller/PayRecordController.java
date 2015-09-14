package com.xiaba2.bullfight.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.Arena;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.PayRecord;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.MatchFightService;
import com.xiaba2.bullfight.service.PayRecordService;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;

/**
 * @author goddie 6p4t58mkqxtfyso57xr3mvl161uxfjcl 2088911907194201
 */
@RestController
@RequestMapping("/payrecord")
public class PayRecordController {
	@Resource
	private PayRecordService payRecordService;

	@Resource
	private UserService userService;

	@Resource
	private MatchFightService matchFightService;
	
	
	@Resource
	private TeamService teamService;

	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_payrecord_" + name);
	}

	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList(PayRecord entity, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("admin_payrecord_list");

		Page<PayRecord> page = new Page<PayRecord>();
		page.setPageSize(10);
		page.setPageNo(1);

		DetachedCriteria criteria = payRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		page = payRecordService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		return mv;
	}

	@RequestMapping("/json/add")
	public JsonResult jsonAdd(PayRecord entity,
			@RequestParam("uid") String uid, @RequestParam("mid") String mid,
			HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		User user = userService.get(UUID.fromString(uid));
		MatchFight matchFight = matchFightService.get(UUID.fromString(mid));
		entity.setUser(user);
		entity.setMatchFight(matchFight);
		entity.setCreatedDate(new Date());

		payRecordService.save(entity);

		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("支付成功");
		return rs;
	}

	/**
	 * 应战记录
	 * 
	 * @param mfid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/getrecord")
	public JsonResult jsonGetRecord(@RequestParam("mfid") UUID mfid,
			HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		MatchFight matchFight = matchFightService.get(mfid);

		DetachedCriteria criteria = payRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		criteria.add(Restrictions.eq("status", 0));
		criteria.add(Restrictions.isNull("user"));

		List<PayRecord> list = payRecordService.findByCriteria(criteria);

		if (list == null || list.size() == 0) {
			return rs;
		}

		rs.setData(list.get(0));
		rs.setCode(JsonResult.SUCCESS);
		return rs;
	}

	/**
	 * 主队支付
	 * 
	 * @param mfid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/payhost")
	public JsonResult jsonPayHost(@RequestParam("mfid") UUID mfid,
			@RequestParam("uid") UUID uid, HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		MatchFight matchFight = matchFightService.get(mfid);
		User user = userService.get(uid);

		DetachedCriteria criteria = payRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		criteria.add(Restrictions.eq("user", user));
		// criteria.add(Restrictions.eq("status", 0));

		List<PayRecord> list = payRecordService.findByCriteria(criteria);

		if (list == null || list.size() == 0) {
			return rs;
		}

		PayRecord entity = list.get(0);
		entity.setStatus(1);
		payRecordService.saveOrUpdate(entity);

		matchFight.setIsPay(1);
		matchFightService.saveOrUpdate(matchFight);

		// rs.setData(list.get(0));
		rs.setCode(JsonResult.SUCCESS);
		return rs;
	}
	
	
	
	/**
	 * 客队支付
	 * @param mfid
	 * @param uid
	 * @param request
	 * @return
	 */
	@RequestMapping("/json/payguest")
	public JsonResult jsonPayGuest(@RequestParam("mfid") UUID mfid,
			@RequestParam("uid") UUID uid,
			@RequestParam("tid") UUID tid, HttpServletRequest request) {
		JsonResult rs = new JsonResult();

		MatchFight matchFight = matchFightService.get(mfid);
		User user = userService.get(uid);

		DetachedCriteria criteria = payRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("matchFight", matchFight));
		criteria.add(Restrictions.isNull("user"));
		// criteria.add(Restrictions.eq("status", 0));

		List<PayRecord> list = payRecordService.findByCriteria(criteria);

		if (list == null || list.size() == 0) {
			return rs;
		}

		PayRecord entity = list.get(0);
		entity.setStatus(1);
		entity.setUser(user);
		payRecordService.saveOrUpdate(entity);

		
		Team team = teamService.get(tid);
		matchFight.setGuest(team);
		matchFight.setStatus(1);
		matchFightService.saveOrUpdate(matchFight);

		// rs.setData(list.get(0));
		rs.setCode(JsonResult.SUCCESS);
		return rs;
	}

}