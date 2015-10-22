package com.xiaba2.cms.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.xiaba2.cms.domain.Member;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.MemberService;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;

import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/member")
public class MemberController {
	@Resource
	private MemberService memberService;

	@Resource
	private UserService userService;

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/page/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("member_" + name);
	}
	
	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/reg")
	public ModelAndView pageReg() {
		return new ModelAndView("admin_reg");
	}
	
	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/login")
	public ModelAndView pageLogin() {
		return new ModelAndView("admin_login");
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(Member entity, RedirectAttributes attr, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		DetachedCriteria criteria = memberService.createDetachedCriteria();

		criteria.add(Restrictions.eq("username", entity.getUsername()));
		criteria.add(Restrictions.eq("password", entity.getPassword()));

		List<Member> list = memberService.findByCriteria(criteria);

		if (!list.isEmpty()) {
			// attr.addAttribute("loginMember", list.get(0).getId().toString());

			DetachedCriteria criteria2 = userService.createDetachedCriteria();
			criteria.add(Restrictions.eq("username", entity.getUsername()));
			criteria.add(Restrictions.eq("password", entity.getPassword()));

			List<User> list2 = userService.findByCriteria(criteria2);
			
			
			

			WebUtils.setSessionAttribute(request, "member", list.get(0));
			WebUtils.setSessionAttribute(request, "user", list2.get(0));

			// session.setAttribute("member", list.get(0));
			// session.setAttribute("user", list2.get(0));

			attr.addFlashAttribute("msg", "登录成功!");
			mv.setViewName("redirect:/page/ucenter_userconfig.jsp");
		} else {
			attr.addFlashAttribute("msg", "账号不存在!");
			mv.setViewName("redirect:/member/admin/login");
		}

		return mv;
	}

	/**
	 * 注册
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/reg")
	public ModelAndView reg(Member entity, RedirectAttributes attr, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		entity.setEmail(entity.getUsername());
		entity.setRegIp(HttpUtil.getIpAddr(request));
		entity.setRegTime(new Date());
		memberService.save(entity);
		attr.addFlashAttribute("msg", "帐号重复存在!");
		mv.setViewName("redirect:/member/admin/login");
		return mv;
	}

	@RequestMapping(value = "/list")
	public ModelAndView list(RedirectAttributes attr) {

		List<Member> list = memberService.loadAll();

		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.addObject("loginMember", attr);
		mv.setViewName("member_list");

		return mv;
	}

	/**
	 * 登录
	 * 
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/login")
	public JsonResult jsonLogin(Member entity, HttpServletRequest request) {

		DetachedCriteria criteria = memberService.createDetachedCriteria();

		criteria.add(Restrictions.eq("username", entity.getUsername()));
		criteria.add(Restrictions.eq("password", entity.getPassword()));

		List<Member> list = memberService.findByCriteria(criteria);

		if (!list.isEmpty()) {
			// attr.addAttribute("loginMember", list.get(0).getId().toString());

			DetachedCriteria criteria2 = userService.createDetachedCriteria();
			criteria2.add(Restrictions.eq("member", list.get(0)));

			List<User> list2 = userService.findByCriteria(criteria2);

			if (!list2.isEmpty()) {

				JsonResult rs = new JsonResult();
				rs.setCode(1);
				rs.setData(list2.get(0));
				rs.setMsg("登录成功!");

				return rs;
			}

		}

		return new JsonResult();
	}

	/**
	 * 注册
	 * 
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/reg")
	public JsonResult jsonReg(Member entity, HttpServletRequest request) {

		if (StringUtils.isEmpty(entity.getUsername())) {

			JsonResult rs = new JsonResult();
			rs.setCode(JsonResult.FAIL);
			rs.setMsg("注册失败");
			return rs;

		}

		entity.setEmail(entity.getUsername());
		entity.setRegIp(HttpUtil.getIpAddr(request));
		entity.setRegTime(new Date());
		memberService.save(entity);

		User user = userService.getByMember(entity);

		if (user == null) {

			JsonResult rs = new JsonResult();
			rs.setCode(JsonResult.FAIL);
			rs.setMsg("注册失败");
			return rs;
		}

		JsonResult rs = new JsonResult();
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(user);

		return rs;
	}

}
