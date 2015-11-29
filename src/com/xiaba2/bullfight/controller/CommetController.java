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
import com.xiaba2.bullfight.service.MessageService;
import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.ArticleService;
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
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private MessageService messageService;
	
	/**
	 * 发表评论
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(@RequestParam("uid") UUID uid,@RequestParam("content") String content,HttpServletRequest request) 
	{
		JsonResult rs = new JsonResult();

		Commet entity = new Commet();
		
		User from = userService.get(uid);
			
		String mfid = request.getParameter("mfid");
		String aid = request.getParameter("aid");
		
		
		//是比赛评论
		if(!StringUtils.isEmpty(mfid))
		{
			MatchFight matchFight = matchFightService.get(UUID.fromString(mfid));
			entity.setMatchFight(matchFight);
		}
		
		//是文章评论
		if(!StringUtils.isEmpty(aid))
		{
			Article article = articleService.get(UUID.fromString(aid));
			entity.setArticle(article);
		}
		
		
		
		entity.setFrom(from);
		entity.setContent(content);
		
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
		
		
		
		//有回复要发推送
		if(entity.getReply()!=null)
		{
			Message message = new Message();
			
			if(entity.getArticle()!=null)
			{
				message.setContent("新闻："+ entity.getFrom().getNickname() +"在【"+ entity.getArticle().getTitle() +"】回复了您！");
				message.setType(3);
			}

			if(entity.getMatchFight()!=null)
			{
				String s = entity.getMatchFight().getMatchType() == 1?"团队约战":"野球约战";
				message.setContent("约战："+ entity.getFrom().getNickname() +"【"+s+"】回复了您！");
				message.setType(4);
			}

			message.setFrom(from);
			message.setCommet(entity);
			message.setTitle(message.getContent());
			message.setCreatedDate(new Date());
			message.setSendTo(entity.getReply());
			message.setIsPush(0);
			
			messageService.save(message);
			messageService.pushMessage(message);
		}
		else //正常评论
		{
			if(entity.getMatchFight()!=null)
			{
				Message message = new Message();
				
				String s = entity.getMatchFight().getMatchType() == 1?"团队约战":"野球约战";
				message.setContent("约战："+ entity.getFrom() +"【"+s+"】发表了评论！");
				message.setType(5);
				
				
				message.setFrom(from);
				message.setSendTo(entity.getMatchFight().getHost().getAdmin());
				message.setCommet(entity);
				message.setTitle(message.getContent());
				message.setCreatedDate(new Date());
				
				message.setIsPush(0);
				
				messageService.save(message);
				messageService.pushMessage(message);
			}

			
		}
		

		
		return rs;
		
	}
	
	
	
	/**
	 * 评论列表
	 * @param mfid
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("p") int p,HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		
		DetachedCriteria criteria = commetService.createDetachedCriteria();
		
		String mfid = request.getParameter("mfid");
		String aid = request.getParameter("aid");
		
		
		//是比赛评论
		if(!StringUtils.isEmpty(mfid))
		{
			MatchFight matchFight = matchFightService.get(UUID.fromString(mfid));
			criteria.add(Restrictions.eq("matchFight", matchFight));
		}
		
		//是文章评论
		if(!StringUtils.isEmpty(aid))
		{
			Article article = articleService.get(UUID.fromString(aid));
			criteria.add(Restrictions.eq("article", article));
		}
		
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