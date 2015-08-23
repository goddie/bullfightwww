package com.xiaba2.cms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.bullfight.domain.TeamUser;
import com.xiaba2.bullfight.service.TeamService;
import com.xiaba2.bullfight.service.TeamUserService;
import com.xiaba2.cms.domain.Member;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.MemberService;
import com.xiaba2.cms.service.UserService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SendSMS;
import com.xiaba2.util.SendSMSYUNPIAN;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	private MemberService memberService;
	
	@Resource
	private TeamUserService teamUserService;
	
	@Resource
	private TeamService teamService;

	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_user_" + name);
	}

	/**
	 * 选择成员
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/sel")
	public ModelAndView adminSel(HttpServletRequest request) {
		
		String tid = request.getParameter("tid");
		
		

		ModelAndView mv = new ModelAndView("admin_user_sel");

		
		if(!StringUtils.isEmpty(tid))
		{
			
			Team team = teamService.get(UUID.fromString(tid));
			List<User> list1 = teamUserService.getUsersByTeam(team);
			
			mv.addObject("list", list1);
			return mv;

		}
		
		
		
		
		Page<User> page = new Page<User>();
		
		page.setPageSize(999);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");
		

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		 
		
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

//		User regUser = new User();
//
//		regUser.setNickname(entity.getNickname());
//		regUser.setCity(entity.getCity());
//		regUser.setPosition(entity.getPosition());
//		regUser.setAvatar(entity.getAvatar());
		
		entity.setCreatedDate(new Date());
		
		// entity = member.getUser();

		// entity.setCreatedDate(new Date());
		// userService.save(entity);
		userService.saveOrUpdate(entity);

		return mv;
	}

	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList() {

		ModelAndView mv = new ModelAndView("admin_user_list");

		Page<User> page = new Page<User>();
		page.setPageSize(999);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		page = userService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());
		
		
		List<String> list = new ArrayList<String>();
		
		
		for (User u : page.getResult()) {
			
			List<Team> list2 = teamUserService.getTeamsByUser(u);
			
			StringBuilder sb=new StringBuilder();
			
			if(list2==null)
			{
				continue;
			}
			
			for (Team team : list2) {
				sb.append(team.getName());
				sb.append(",");
			}
			
			list.add(sb.toString());
		}
		

		mv.addObject("teamList", list);
		
		

		return mv;
	}
	
	
	
	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("uid") UUID uid) {

		ModelAndView mv = new ModelAndView("redirect:/user/admin/list");
		
		User u = userService.get(uid);
		
		u.setIsDelete(1);
		
		userService.saveOrUpdate(u);
		

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
		criteria.add(Restrictions.eq("isDelete", 0));
		page = userService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		return mv;
	}

	Page<User> getPageEntity(Page<User> page) {
		page.setPageSize(10);
		page.setPageNo(1);

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		page = userService.findPageByCriteria(criteria, page);

		return page;
	}

	@RequestMapping(value = "/json/reg")
	public JsonResult jsonReg(@RequestParam("phone") String phone) {
		JsonResult rs = new JsonResult();

		if (StringUtils.isEmpty(phone)) {
			rs.setMsg("请输入手机号");
			return rs;
		}

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("username", phone));

		List<User> list = userService.findByCriteria(criteria);
		if (!list.isEmpty()) {
			rs.setMsg("手机号已被注册");
			return rs;
		}

		User entity = new User();

		entity.setCreatedDate(new Date());
		entity.setUsername(phone);
		entity.setPhone(phone);

		userService.save(entity);

		rs.setMsg("注册成功");
		rs.setData(entity);
		rs.setCode(JsonResult.SUCCESS);

		return rs;
	}

	@RequestMapping(value = "/json/getuser")
	public JsonResult jsonGetUser(@RequestParam("uid") String uid) {
		JsonResult rs = new JsonResult();

		if (StringUtils.isEmpty(uid)) {
			rs.setMsg("请输入手机号");
			return rs;
		}

		User entity = userService.get(UUID.fromString(uid));

		rs.setData(entity);
		rs.setCode(JsonResult.SUCCESS);

		return rs;
	}

	@RequestMapping(value = "/json/login")
	public JsonResult jsonLogin(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		JsonResult rs = new JsonResult();

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			rs.setMsg("请输入手机号");
			return rs;
		}

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", password));

		List<User> list = userService.findByCriteria(criteria);
		if (list.isEmpty()) {
			rs.setMsg("登录失败");
			return rs;
		}

		User entity = list.get(0);

		rs.setMsg("登录成功");
		rs.setData(entity);
		rs.setCode(JsonResult.SUCCESS);

		return rs;
	}

	/**
	 * 注册时完善资料
	 * 
	 * @param uid
	 * @param position
	 * @param city
	 * @return
	 */
	@RequestMapping(value = "/json/regthree")
	public JsonResult jsonRegThree(@RequestParam("uid") String uid,
			@RequestParam("position") String position,
			@RequestParam("city") String city) {
		JsonResult rs = new JsonResult();

		if (StringUtils.isEmpty(uid)) {
			rs.setMsg("请先登录");
			return rs;
		}

		User entity = userService.get(UUID.fromString(uid));

		if (!StringUtils.isEmpty(position)) {
			entity.setPosition(position);
		}

		if (!StringUtils.isEmpty(city)) {
			entity.setCity(city);
		}

		userService.saveOrUpdate(entity);

		rs.setMsg("注册成功");
		rs.setData(entity);
		rs.setCode(JsonResult.SUCCESS);

		return rs;
	}

	@RequestMapping(value = "/json/regtwo")
	public JsonResult jsonRegTwo(@RequestParam("uid") String uid,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		JsonResult rs = new JsonResult();

		if (StringUtils.isEmpty(uid)) {
			rs.setMsg("请先登录");
			return rs;
		}

		User entity = userService.get(UUID.fromString(uid));

		if (!StringUtils.isEmpty(username)) {
			entity.setUsername(username);
		}

		if (!StringUtils.isEmpty(password)) {
			entity.setPassword(password);
		}

		userService.saveOrUpdate(entity);

		rs.setMsg("注册成功");
		rs.setData(entity);
		rs.setCode(JsonResult.SUCCESS);

		return rs;
	}

	/**
	 * 发送短信
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/json/regsms")
	public JsonResult jsonRegSms(@RequestParam("phone") String phone) {
		JsonResult rs = new JsonResult();
		// rs.setCode(JsonResult.SUCCESS);
		// return rs;
		
		if (StringUtils.isEmpty(phone)) {
			rs.setMsg("请输入手机号");
			return rs;
		}

		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号
		m = p.matcher(phone);

		if (!m.matches()) {
			rs.setMsg("手机号不正确");
			return rs;
		}



		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("phone", phone));

		List<User> list = userService.findByCriteria(criteria);
		if (!list.isEmpty()) {
			
			
			if(!list.get(0).getUsername().equals(phone))
			{
				rs.setMsg("该手机已注册");
				return rs;
				
			}

		}

		StringBuilder sb = new StringBuilder();
		sb.append(SendSMS.getRand());
		sb.append(SendSMS.getRand());
		sb.append(SendSMS.getRand());
		sb.append(SendSMS.getRand());
		sb.append(SendSMS.getRand());
		sb.append(SendSMS.getRand());

		String mess = "【来斗牛】您的验证码是" + sb.toString() + "。如非本人操作，请忽略本短信";

		// String mess = "您的校验码是：" + sb.toString() +
		// "。请不要把校验码泄露给其他人。如非本人操作，可不用理会！";

		// String code = "2";
		String code = SendSMSYUNPIAN.sendSMSYUNPIAN(phone, mess);

		if (!code.endsWith("0")) {
			
			rs.setMsg("短信发送失败");
			return rs;
		}
		
		User user = new User();

		user.setNickname("来斗牛玩家");
		user.setPhone(phone);
		user.setUsername(phone);
		user.setPassword(sb.toString());
		user.setCreatedDate(new Date());
		user.setLastModifiedBy(sb.toString());

		userService.saveOrUpdate(user);

		rs.setMsg("已发送短信到手机:"+phone);
		rs.setCode(JsonResult.SUCCESS);
		return rs;
	}
	
	/**
	 * 验证码检测
	 * @param phone
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/json/regcheck")
	public JsonResult jsonCheck(@RequestParam("phone") String phone,@RequestParam("code") String code) {
		JsonResult rs = new JsonResult();
		
		//测试用的数字串
		if(code.equals("19491001"))
		{
			DetachedCriteria criteria = userService.createDetachedCriteria();
			criteria.add(Restrictions.eq("isDelete", 0));
 			criteria.add(Restrictions.eq("phone", phone));
			List<User> list = userService.findByCriteria(criteria);
			if (list.isEmpty()) {
				return rs;
			}
	 		User entity = list.get(0);
			rs.setData(entity);
			rs.setCode(JsonResult.SUCCESS);
			return rs;
		}

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("username", phone));
		criteria.add(Restrictions.eq("phone", phone));
		criteria.add(Restrictions.eq("password", code));

		List<User> list = userService.findByCriteria(criteria);
		if (list.isEmpty()) {
			rs.setMsg("验证码有误");
			return rs;
		}

 		User entity = list.get(0);
//
//		entity.setCreatedDate(new Date());
//		entity.setUsername(phone);
//		entity.setPhone(phone);
//
//		userService.save(entity);
//
//		rs.setMsg("注册成功");
		rs.setData(entity);
		rs.setCode(JsonResult.SUCCESS);

		return rs;
	}
	

	
	/**
	 * 查询
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/json/search")
	public JsonResult jsonSearch(@RequestParam("nickname") String nickname) 
	{
		JsonResult rs = new JsonResult();
		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("nickname", nickname));
		
		List<User> list = userService.findByCriteria(criteria);
		if(list==null||list.size()==0)
		{
			rs.setMsg("查无此人");
			return rs;
		}
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list);
		return rs;
		
	}
	
	
	
	

}