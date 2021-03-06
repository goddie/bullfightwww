package com.xiaba2.cms.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpUtils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sun.swing.StringUIClientPropertyKey;

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

//	@RequestMapping(value = "/admin/{name}")
//	public ModelAndView getPage(@PathVariable String name) {
//		return new ModelAndView("admin_user_" + name);
//	}
	
	
	@RequestMapping(value = "/admin/add")
	public ModelAndView adminAdd() {
		return new ModelAndView("admin_user_add");
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
		
		 
		String key = request.getParameter("key");
		if(!StringUtils.isEmpty(key))
		{
			criteria.add(Restrictions.like("nickname",key,MatchMode.ANYWHERE));
		}
		
		page = userService.findPageByCriteria(criteria, page);


		mv.addObject("list", page.getResult());

		return mv;

	}

	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(User entity, HttpServletRequest request,RedirectAttributes attr) {

		ModelAndView mv = new ModelAndView("admin_user_add");

//		Member member = new Member();
//
//		member.setUsername(entity.getUsername());
//		member.setPassword(entity.getPassword());
//		member.setRegIp(HttpUtil.getIpAddr(request));
//		member.setRegTime(new Date());
//		memberService.save(member);

//		User regUser = new User();
//
//		regUser.setNickname(entity.getNickname());
//		regUser.setCity(entity.getCity());
//		regUser.setPosition(entity.getPosition());
//		regUser.setAvatar(entity.getAvatar());
		
		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("username", entity.getUsername()));
		
		List<User> list = userService.findByCriteria(criteria);
		if(!list.isEmpty())
		{
			attr.addFlashAttribute("msg", "用户名已存在");
			return mv;
		}
		
		
		entity.setCreatedDate(new Date());
		
		// entity = member.getUser();

		// entity.setCreatedDate(new Date());
		userService.save(entity);
//		userService.saveOrUpdate(entity);

		return mv;
	}
	
	@RequestMapping(value = "/admin/edit")
	public ModelAndView adminEdit(@RequestParam("uid") UUID uid, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("admin_user_edit");

		User entity = userService.get(uid);
		mv.addObject("entity",entity);
		return mv;
	}
	
	
	@RequestMapping(value = "/action/edit")
	public ModelAndView actionEdit(User entity, HttpServletRequest request,RedirectAttributes attr) {

		ModelAndView mv = new ModelAndView("redirect:/user/admin/list?p=1");
		
//		DetachedCriteria criteria = userService.createDetachedCriteria();
//		criteria.add(Restrictions.eq("isDelete", 0));
//		criteria.add(Restrictions.eq("username", entity.getUsername()));
//
//		
//		List<User> list = userService.findByCriteria(criteria);
//		if(!list.isEmpty())
//		{
//			attr.addFlashAttribute("msg", "用户名已存在");
//			return mv;
//		}
		
		
		User user = userService.get(entity.getId());
		
//		user.setUsername(entity.getUsername());
		user.setPassword(entity.getPassword());
		user.setAvatar(entity.getAvatar());
		user.setPhone(entity.getPhone());
		user.setPosition(entity.getPosition());
		user.setNickname(entity.getNickname());
		user.setCity(entity.getCity());
		
		
		//BeanUtils.copyProperties(entity,user,new String[]{"id"});
		
		userService.saveOrUpdate(user);

		return mv;
	}

	@RequestMapping(value = "/admin/list")
	public ModelAndView pageList(@RequestParam("p") int p, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("admin_user_list");

		Page<User> page = new Page<User>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		
		HttpUtil.addSearchLike(criteria, mv, request, "username");
		HttpUtil.addSearchLike(criteria, mv, request, "nickname");
		HttpUtil.addSearchLike(criteria, mv, request, "phone");
 
		
		
		
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
		mv.addObject("pageHtml",page.genPageHtml(request));

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
	public ModelAndView pageRecordList(@RequestParam("p") int p, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("admin_user_recordlist");

		Page<User> page = new Page<User>();
		page.setPageSize(15);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		String key = request.getParameter("key");
		if(!StringUtils.isEmpty(key))
		{
			criteria.add(Restrictions.like("nickname",key,MatchMode.ANYWHERE));
		}
		
		page = userService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml",page.genPageHtml(request));

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
		criteria.add(Restrictions.eq("phone", phone));

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

	/**
	 * 用户信息
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/json/getuser")
	public JsonResult jsonGetUser(@RequestParam("uid") String uid) {
		JsonResult rs = new JsonResult();

		if (StringUtils.isEmpty(uid)) {
			return rs;
		}

		User entity = userService.get(UUID.fromString(uid));

		if(entity==null)
		{
			return rs;
		}
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
		if (!list.isEmpty()) {
//			rs.setMsg("登录失败");
//			return rs;
			User entity = list.get(0);

			rs.setMsg("登录成功");
			rs.setData(entity);
			rs.setCode(JsonResult.SUCCESS);
		}
		
		
		
		DetachedCriteria criteria2 = userService.createDetachedCriteria();
		criteria2.add(Restrictions.eq("isDelete", 0));
		criteria2.add(Restrictions.eq("phone", username));
		criteria2.add(Restrictions.eq("password", password));

		List<User> list2 = userService.findByCriteria(criteria2);
		if (!list2.isEmpty()) {
//			rs.setMsg("登录失败");
//			return rs;
			User entity = list2.get(0);

			rs.setMsg("登录成功");
			rs.setData(entity);
			rs.setCode(JsonResult.SUCCESS);
		}

		
		rs.setMsg("登录失败");
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
			@RequestParam("password") String password,
			@RequestParam("nickname") String nickname) {
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
		
		
		if (!StringUtils.isEmpty(nickname)) {
			entity.setNickname(nickname);
		}
		
		entity.setIsDelete(0);
		entity.setFinishReg(1);

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


		//注册成功了的
		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.or(Restrictions.eq("username", phone), Restrictions.eq("phone", phone)));
//		criteria.add(Restrictions.eq("username", phone));
//		criteria.add(Restrictions.eq("phone", phone));
		criteria.add(Restrictions.eq("finishReg", 1));
		
		Page<User> page = new Page<User>();
		page.setPageSize(1);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");
		
		
		page = userService.findPageByCriteria(criteria, page);

		List<User> list = page.getResult();

		if (list!=null && list.size()>0) {
			rs.setMsg("该手机已注册");
			return rs;
//			else
//			{
//				user = list.get(0);
//			}

		}
		
		
		
		//尚未注册成功
		
//		else
//		{
//			user = new User();
//		}

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
		
		User user  = new User();

		user.setNickname("来斗牛玩家");
		user.setPhone(phone);
		user.setUsername(phone);
		//验证码
		user.setPassword(sb.toString());
		user.setCreatedDate(new Date());
		user.setLastModifiedBy(sb.toString());
		user.setIsDelete(1);
		user.setFinishReg(0);

		userService.saveOrUpdate(user);

		rs.setMsg("已发送短信到手机:"+phone);
		rs.setCode(JsonResult.SUCCESS);
		return rs;
	}
	
	
	
	
	
	/** 
	 * 找回密码 短信验证
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/json/pswdsms")
	public JsonResult jsonPswdSms(@RequestParam("phone") String phone) {
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
		
		
		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("phone", phone));

		List<User> list = userService.findByCriteria(criteria);
		if (list.isEmpty()) {
			return rs;
		}
		
		
		User user = list.get(0);
		user.setPayPassword(sb.toString());
		userService.saveOrUpdate(user);

		rs.setMsg("已发送短信到手机:"+phone);
		rs.setCode(JsonResult.SUCCESS);
		return rs;
	}
	
	
	
	/**
	 * 修改密码 验证码检测
	 * @param phone
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/json/pswdcheck")
	public JsonResult jsonPswdCheck(@RequestParam("phone") String phone,@RequestParam("code") String code) {
		JsonResult rs = new JsonResult();
		
		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("phone", phone));
		criteria.add(Restrictions.eq("payPassword", code));

		List<User> list = userService.findByCriteria(criteria);
		if (list.isEmpty()) {
			rs.setMsg("验证码有误");
			return rs;
		}

 		User entity = list.get(0);

		rs.setData(entity);
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
				
				User user = new User();

				user.setNickname("来斗牛玩家");
				user.setPhone(phone);
				user.setUsername(phone);
				user.setPassword("19491001");
				user.setCreatedDate(new Date());


				userService.saveOrUpdate(user);
				
				list = new ArrayList<User>();
				list.add(user);
				
			}
	 		User entity = list.get(0);
			rs.setData(entity);
			rs.setCode(JsonResult.SUCCESS);
			return rs;
		}

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 1));
		criteria.add(Restrictions.eq("username", phone));
		criteria.add(Restrictions.eq("phone", phone));
		criteria.add(Restrictions.eq("password", code));
		criteria.add(Restrictions.eq("finishReg", 0));
		
		Page<User> page = new Page<User>();
		page.setPageSize(1);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");
		
		
		page = userService.findPageByCriteria(criteria, page);

		List<User> list = page.getResult();
		
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
		criteria.add(Restrictions.like("nickname", nickname,MatchMode.ANYWHERE));
		
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
	
	
	
	/**
	 * 上传头像
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/upavatar")
	public JsonResult jsonUpavatar(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {

		JsonResult rs = new JsonResult();

		String uid = request.getParameter("uid");
		if (StringUtils.isEmpty(uid)) {
			return rs;
		}

		// String path = System.getProperty("myapp.root") + "upload";
		String path = this.getClass().getClassLoader().getResource("/")
				.getPath();
		path = path.replace("WEB-INF" + File.separator + "classes"
				+ File.separator, "upload");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String pdate = sdf.format(new Date());
		path = path + File.separator + pdate;
		String ext = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf("."));
		String fileName = UUID.randomUUID().toString().replace("-", "") + ext;
		// String fileName = new Date().getTime()+".jpg";
		Logger.getLogger(AlbumController.class.toString())
				.log(Level.INFO, path);
		// System.out.println(path);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String extPath = request.getContextPath() + "/upload/" + pdate + "/";
		
		User user = userService.get(UUID.fromString(uid));
		
		
		user.setAvatar(extPath+fileName);
		
		userService.saveOrUpdate(user);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(user);
		
		return rs;
	}
	
	
	@RequestMapping(value = "/json/update")
	public JsonResult jsonUpdate(@RequestParam("uid") UUID uid, HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		
		User user = userService.get(uid);
		
		
		user.setNickname(request.getParameter("nickname"));
		user.setCity(request.getParameter("city"));
		user.setAge((int)Float.parseFloat(request.getParameter("age")));
		user.setHeight((int)Float.parseFloat(request.getParameter("height")));
		user.setWeight((int)Float.parseFloat(request.getParameter("weight")));
		
		String pos = request.getParameter("position");
		
		if(!StringUtils.isEmpty(pos))
		{
			user.setPosition(request.getParameter("position"));
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date birthday;
 
		try {

			birthday = sdf.parse(request.getParameter("birthday"));
			user.setBirthday(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		userService.saveOrUpdate(user);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(user);
		rs.setMsg("修改成功!");
	
		return rs;
	}

	@RequestMapping(value = "/json/uppassword")
	public JsonResult jsonUpPassword(@RequestParam("uid") UUID uid, @RequestParam("password") String password) {
		JsonResult rs = new JsonResult();
		
		User user = userService.get(uid);
		
		user.setPassword(password);
		 
		userService.saveOrUpdate(user);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(user);
		rs.setMsg("修改成功!");
	
		return rs;
	}
	
	
	
	/**
	 * 查询
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("p") int p) 
	{
		JsonResult rs = new JsonResult();
		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		
		Page<User> page = new Page<User>();
		page.setPageSize(15);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");
		
		
		page = userService.findPageByCriteria(criteria, page);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());
		
		return rs;
		
	}
	
	
	/**
	 * 查询
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/json/searchuser")
	public JsonResult jsonSearchUser(@RequestParam("nickname") String nickname,@RequestParam("p") int p) 
	{
		JsonResult rs = new JsonResult();
		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.like("nickname", nickname,MatchMode.ANYWHERE));
		
		Page<User> page = new Page<User>();
		page.setPageSize(15);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");
		
		page = userService.findPageByCriteria(criteria, page);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());
		return rs;
		
	}
	
	
	
}