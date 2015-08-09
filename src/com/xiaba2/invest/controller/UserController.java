package com.xiaba2.invest.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.cms.domain.Member;
import com.xiaba2.cms.service.MemberService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.UserService;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
 

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_user_" + name);
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(User entity, HttpServletRequest request) {

//		Member member = new Member();
//		member.setUsername(request.getParameter("username"));
//		member.setPassword(request.getParameter("password"));
//		member.setCreatedDate(new Date());
//		memberService.save(member);
//
//		User u = userService.getByMember(member);
//
//		entity.setCreatedDate(new Date());
//		entity.setMember(member);
//		entity.setId(u.getId());
//		userService.saveOrUpdate(entity);

		return new ModelAndView("redirect:/user/admin/add");

	}

	/**
	 * 获取列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("p") int p) {
		p = Math.max(p, 1);

		Page<User> page = new Page<User>();
		page.setPageNo(p);
		page.setPageSize(10);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		page = userService.findPageByCriteria(criteria, page);

		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}
	
	
	
	/**
	 * 注册
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/reg")
	public JsonResult jsonReg(User entity,HttpServletRequest request) {

		
		if (StringUtils.isEmpty(entity.getUsername())) {
			
			JsonResult rs =new JsonResult();
			rs.setCode(JsonResult.FAIL);
			rs.setMsg("注册失败");
			return rs;
			
		}
		
		entity.setNickname(entity.getUsername());
		entity.setEmail(entity.getUsername());
		entity.setRegIp(HttpUtil.getIpAddr(request));
		entity.setCreatedDate(new Date());
		
		
		userService.save(entity);
		
		
		if (entity.getId()==null) {
			
			JsonResult rs =new JsonResult();
			rs.setCode(JsonResult.FAIL);
			rs.setMsg("注册失败");
			return rs;
		}
		
		JsonResult rs =new JsonResult();
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(entity);
		rs.setMsg("注册成功!");
		
		return rs;
	}
	
	/**
	 * 登录
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/login")
	public JsonResult jsonLogin(User entity,HttpServletRequest request) {

		DetachedCriteria criteria = userService.createDetachedCriteria();

		criteria.add(Restrictions.eq("username", entity.getUsername()));
		criteria.add(Restrictions.eq("password", entity.getPassword()));

		List<User> list = userService.findByCriteria(criteria);
		
		if (!list.isEmpty()) {
			
			User user = list.get(0);
			
			JsonResult rs = new JsonResult();
			rs.setCode(JsonResult.SUCCESS);
			rs.setData(user);
			rs.setMsg("登录成功!");
			
			return rs;
			
		} 
		
		return new JsonResult("登录失败!");
	}

	/**
	 * 具体玩家信息
	 * 
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/json/detail")
	public JsonResult jsonDetail(HttpServletRequest request) {
		
		String uid = request.getParameter("uid");
		
		User u = null;
		
		if (!StringUtils.isEmpty(uid)) {
			UUID id = UUID.fromString(uid);
			u = userService.get(id);
		}
		
 

		if (u == null) {
			return new JsonResult();
		}

		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(u);

		return rs;
	}
	
	
	/**
	 * 推荐红人
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/advice")
	public JsonResult jsonAdvice() {
	
		List<User> list = userService.loadAll();
		
		Random ra =new Random();
		
		int idx = ra.nextInt(list.size()-1);
		
		User u = list.get(idx);
		
		if (u==null) {
			return new JsonResult();
		}

		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list.get(idx));

		return rs;
	}
	

}