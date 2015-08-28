package com.xiaba2.bullfight.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Message;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.MatchFightService;
import com.xiaba2.bullfight.service.MessageService;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.bullfight.service.TeamUserService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;

@RestController
@RequestMapping("/message")
public class MessageController {
	@Resource
	private MessageService messageService;

	@Resource
	private UserService userService;
	
	@Resource
	private MatchFightService matchFightService;
	
	
	@Resource
	private TeamUserService teamUserService;
	
	
	@Resource
	private TeamService teamService;
	
	
	/**
	 * 通知队长
	 * @param uid
	 * @param mfid
	 * @return
	 */
	@RequestMapping(value = "/json/sendtoteam")
	public JsonResult jsonSendToTeam(@RequestParam("uid") String uid,
			@RequestParam("mfid") String mfid) {
		JsonResult rs = new JsonResult();

		if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(mfid)) {
			return rs;
		}

		Message entity = new Message();
		
		User from = userService .get(UUID.fromString(uid));
		
		MatchFight mf = matchFightService.get(UUID.fromString(mfid));
		
		
		Team team = teamUserService.getTeamByUser(from);
		if(team==null)
		{
			rs.setMsg("你没有加入队伍");
			return rs;
		}
		
		
		
		entity.setSendTo(team.getAdmin());
		
		entity.setFrom(from);
		entity.setMatchFight(mf);
		entity.setContent("您的队员["+from.getNickname()+"]提醒您有一场比赛可以应战。");
		entity.setTitle(entity.getContent());
		entity.setCreatedDate(new Date());
		entity.setType(1);
		
		messageService.save(entity);
		
		rs.setMsg("已通知队长");
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
	}
	
	/**
	 * 我的消息
	 * @param uid
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/usermessage")
	public JsonResult jsonUserMessage(@RequestParam("uid") String uid,
			@RequestParam("p") int p) {
		JsonResult rs = new JsonResult();

		if (StringUtils.isEmpty(uid)) {
			return rs;
		}
		
		DetachedCriteria criteria = messageService.createDetachedCriteria();
		criteria.add(Restrictions.eq("sendTo.id", UUID.fromString(uid)));
		criteria.add(Restrictions.eq("isDelete", 0));
		Page<Message> page =new Page<Message>();
		page.setPageNo(1);
		page.setPageSize(p);
		page.addOrder("createdDate","desc");
		
		page = messageService.findPageByCriteria(criteria, page);
		
		rs.setData(page.getResult());
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
	}
	
	
	/**
	 * 邀请加入
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/json/invite")
	public JsonResult jsonInvite(@RequestParam("uid") String uid,@RequestParam("tid") String tid) 
	{
		JsonResult rs = new JsonResult();
		
		if (StringUtils.isEmpty(uid)||StringUtils.isEmpty(tid)) {
			return rs;
		}

		Message entity = new Message();
		
		User sendTo = userService.get(UUID.fromString(uid));
		Team team = teamService.get(UUID.fromString(tid));
		User from = team.getAdmin();
		
		if(teamUserService.isUserInTeam(sendTo, team))
		{
			rs.setMsg("该用户已在队伍中");
			return rs;
		}
		
		entity.setSendTo(sendTo);
		entity.setFrom(from);
		entity.setTeam(team);
		entity.setContent(team.getName()+"球队邀请你加入他们的队伍，是否同意加入。");
		entity.setTitle(entity.getContent());
		entity.setCreatedDate(new Date());
		entity.setType(2);
		
		messageService.save(entity);
		
		rs.setMsg("发送成功！");
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
		
	}
	

	/**
	 * 删除消息
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/json/delete")
	public JsonResult jsonDelete(@RequestParam("uid") String uid,@RequestParam("mid") String mid) 
	{
		JsonResult rs = new JsonResult();
		
		if (StringUtils.isEmpty(uid)||StringUtils.isEmpty(mid)) {
			return rs;
		}
		
		
		DetachedCriteria criteria = messageService.createDetachedCriteria();
		criteria.add(Restrictions.eq("sendTo.id", UUID.fromString(uid)));
		criteria.add(Restrictions.eq("id", UUID.fromString(mid)));
		
		List<Message> list = messageService.findByCriteria(criteria);
		if(list!=null&&list.size()>0)
		{
			list.get(0).setIsDelete(1);
			messageService.saveOrUpdate(list);
		}
		
		
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
		
	}
}