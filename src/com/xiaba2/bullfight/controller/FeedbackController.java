package com.xiaba2.bullfight.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.Arena;
import com.xiaba2.bullfight.domain.Feedback;
import com.xiaba2.bullfight.service.FeedbackService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Resource
	private FeedbackService feedbackService;

	@Resource
	private UserService userService;

	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(Feedback entity, HttpServletRequest request) {

		JsonResult rs = new JsonResult();

		entity.setCreatedDate(new Date());

		
		String uidStr = request.getParameter("uid");
		
		if(!StringUtils.isEmpty(uidStr))
		{
			User user = userService.get(UUID.fromString(uidStr));
			entity.setUser(user);
		}
		
		
		feedbackService.save(entity);

		rs.setCode(JsonResult.SUCCESS);
		
		return rs;
	}
}