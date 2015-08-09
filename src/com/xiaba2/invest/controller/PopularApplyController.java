package com.xiaba2.invest.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.core.JsonResult;
import com.xiaba2.invest.domain.PopularApply;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.PopularApplyService;
import com.xiaba2.invest.service.UserService;

@RestController
@RequestMapping("/popularapply")
public class PopularApplyController {
	@Resource
	private PopularApplyService popularApplyService;

	@Resource
	private UserService userService;

	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(@RequestParam("uuid") String uuid) {

		User user = userService.getByMemberUUID(uuid);

		
		PopularApply entity = new PopularApply();
		entity.setUser(user);
		entity.setCreatedDate(new Date());
		popularApplyService.save(entity);

		JsonResult rs = new JsonResult();
		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("提交申请成功!");

		return rs;
	}
}