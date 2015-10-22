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

import com.xiaba2.bullfight.domain.Commet;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.bullfight.domain.Message;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.service.CommetService;
import com.xiaba2.bullfight.service.MatchFightService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;

@RestController
@RequestMapping("/commet")
public class CommetController {
	@Resource
	private CommetService commetService;
	
	
	@Resource
	private UserService userService;
	
	
	@Resource
	private MatchFightService matchFightService;
	
	/**
	 * 发表评论
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(@RequestParam("uid") UUID uid,@RequestParam("content") String content,@RequestParam("mfid") UUID mfid,HttpServletRequest request) 
	{
		JsonResult rs = new JsonResult();
		
		

		Commet entity = new Commet();
		
		User from = userService.get(uid);
		MatchFight matchFight = matchFightService.get(mfid);
		
		
 
		entity.setFrom(from);
		entity.setContent(content);
		entity.setMatchFight(matchFight);
		entity.setCreatedDate(new Date());
 
		String ruid = request.getParameter("ruid");
		
		if(!StringUtils.isEmpty(ruid))
		{
			User reply = userService.get(UUID.fromString(ruid));
			entity.setReply(reply);
		}
		
		commetService.save(entity);
		
		rs.setMsg("发送成功！");
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
		
	}
	
	
	
	/**
	 * 评论列表
	 * @param mfid
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("mfid") UUID mfid,@RequestParam("p") int p) {
		JsonResult rs = new JsonResult();
		MatchFight matchFight = matchFightService.get(mfid);
		
		DetachedCriteria criteria = commetService.createDetachedCriteria();
		criteria.add(Restrictions.eq("matchFight", matchFight));
		criteria.add(Restrictions.eq("isDelete", 0));
		Page<Commet> page =new Page<Commet>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate","desc");
		
		page = commetService.findPageByCriteria(criteria, page);
		
		rs.setData(page.getResult());
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
	}
}