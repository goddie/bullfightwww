package com.xiaba2.invest.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.invest.domain.Message;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.MessageService;
import com.xiaba2.invest.service.UserService;

@RestController
@RequestMapping("/message")
public class MessageController {
	@Resource
	private MessageService messageService;

	@Resource
	private UserService userService;

	/**
	 * 发送消息
	 * 
	 * @param from
	 * @param sendTo
	 * @param content
	 */
	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(@RequestParam("from") String from,
			@RequestParam("sendTo") String sendTo,
			@RequestParam("content") String content) {
		User user1 = userService.get(UUID.fromString(from));
		User user2 = userService.get(UUID.fromString(sendTo));

		Message entity = new Message();

		entity.setCreatedDate(new Date());
		entity.setFrom(user1);
		entity.setSendTo(user2);
		entity.setContent(content);

		messageService.save(entity);

		if (entity.getId() == null) {

			return new JsonResult();
		}

		JsonResult rs = new JsonResult();
		rs.setCode(JsonResult.SUCCESS);

		return rs;

	}

	/**
	 * 聊天记录
	 * 
	 * @param from
	 * @param sendTo
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/getmsg")
	public JsonResult jsonGetMsg(@RequestParam("from") String from,
			@RequestParam("sendTo") String sendTo, @RequestParam("p") int p) {
		p = Math.max(p, 1);

		User user1 = userService.get(UUID.fromString(from));
		User user2 = userService.get(UUID.fromString(sendTo));

		if (user1 == null || user2 == null) {
			return new JsonResult();
		}

		Page<Message> page = new Page<Message>();
		page.setPageNo(p);
		page.setPageSize(10);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = messageService.createDetachedCriteria();

		criteria.add(Restrictions.eq("from", user1));
		criteria.add(Restrictions.eq("sendTo", user2));

		page = messageService.findPageByCriteria(criteria, page);

		if (page.getResult().size()==0) {
			return new JsonResult(); 
		}
		
		
		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;

	}

}