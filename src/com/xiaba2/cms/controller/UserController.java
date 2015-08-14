package com.xiaba2.cms.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.cms.domain.Member;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.MemberService;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.Page;
import com.xiaba2.util.HttpUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	private MemberService memberService;

	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_user_" + name);
	}

	@RequestMapping(value = "/admin/sel")
	public ModelAndView getPageSel() {

		ModelAndView mv = new ModelAndView("admin_user_sel");

		Page<User> page = new Page<User>();
		page.setPageSize(10);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		page = userService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		return mv;

	}

	@RequestMapping(value = "/adminadd")
	public ModelAndView adminAdd(User entity, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("redirect:/page/admin_user_add.jsp");

		Member member = new Member();

		member.setUsername(entity.getUsername());
		member.setPassword(entity.getPassword());
		member.setRegIp(HttpUtil.getIpAddr(request));
		member.setRegTime(new Date());
		memberService.save(member);

		User regUser = member.getUser();

		regUser.setNickname(entity.getNickname());
		regUser.setCity(entity.getCity());
		regUser.setPosition(entity.getPosition());
		regUser.setAvatar(entity.getAvatar());

		// entity = member.getUser();

		// entity.setCreatedDate(new Date());
		// userService.save(entity);
		userService.saveOrUpdate(regUser);

		return mv;
	}

	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList() {

		ModelAndView mv = new ModelAndView("admin_user_list");

		Page<User> page = new Page<User>();
		page.setPageSize(10);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		page = userService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		return mv;
	}
	
	@RequestMapping(value = "/admin/recordlist")
	public ModelAndView pageRecordList() {

		ModelAndView mv = new ModelAndView("admin_user_recordlist");

		Page<User> page = new Page<User>();
		page.setPageSize(10);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		page = userService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		return mv;
	}

	Page<User> getPageEntity(Page<User> page) {
		page.setPageSize(10);
		page.setPageNo(1);

		DetachedCriteria criteria = userService.createDetachedCriteria();
		page = userService.findPageByCriteria(criteria, page);

		return page;
	}
}