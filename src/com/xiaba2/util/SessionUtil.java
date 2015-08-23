package com.xiaba2.util;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaba2.cms.domain.Member;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.MemberService;
import com.xiaba2.cms.service.UserService;

public class SessionUtil {

	// @Resource
	// private HttpSession session;

	// @Resource
	// private HttpServletRequest request;

	private static SessionUtil instance;

	// @Resource
	// private MemberService memberService;
	//
	// @Resource
	// private UserService userService;

	private SessionUtil() {

	}

	public static synchronized SessionUtil getInstance() {
		if (instance == null) {
			instance = new SessionUtil();
		}
		return instance;
	}

	/**
	 * 登录的User
	 * 
	 * @return
	 */
	public User getSessionUser() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		Object sess = WebUtils.getSessionAttribute(request, "user");

		if (sess == null) {

			User user = getUserFromCookie(request);
			if (user == null) {
				return null;
			}

			WebUtils.setSessionAttribute(request, "user", user);
			sess = WebUtils.getSessionAttribute(request, "user");
		}

		User u = null;

		try {
			u = (User) sess;
		} catch (Exception e) {
			Logger.getGlobal().log(Level.INFO, e.getMessage(), e);
		}

		return u;

	}

	/**
	 * 登录的member
	 * 
	 * @return
	 */
	public Member getSessionMember() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();

		Object sess = WebUtils.getSessionAttribute(request, "member");
		if (sess == null) {
			return null;
		}

		Member m = (Member) sess;
		UUID id = m.getId();

		// Member fresh = SessionUtil.getInstance().memberService.get(id);

		return m;

	}

	/**
	 * 用户登录信息
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 */
	public void saveUserCookie(User entity) {

		// HttpServletRequest request = ((ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes())
		// .getRequest();

		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getResponse();

		if (entity == null) {
			Cookie cookie = new Cookie("user", null);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			response.addCookie(cookie);

			return;
		}

		UserCookie mc = new UserCookie();
		mc.setUsername(entity.getUsername());
		mc.setUuid(entity.getId().toString());
		// Cookie cookie = WebUtils.getCookie(request, "login");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
				Boolean.TRUE);
		String json = "";
		try {

			json = mapper.writeValueAsString(mc);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Cookie cookie = new Cookie("user", json);
		cookie.setMaxAge(60 * 60 * 24 * 7);// 保留7天
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * cookie中登录
	 * 
	 * @param request
	 * @return
	 */
	public User getUserFromCookie(HttpServletRequest request) {

		// // 读出用户硬盘上的Cookie，并将所有的Cookie放到一个cookie对象数组里面
		// Cookie cookies[] = request.getCookies();
		//
		// Cookie sCookie=null;
		// String svalue=null;
		// String sname=null;
		//
		// for (int i = 0; i < cookies.length - 1; i++) { //
		// 用一个循环语句遍历刚才建立的Cookie对象数组
		// sCookie = cookies[i]; // 取出数组中的一个Cookie对象
		// sname = sCookie.getName(); // 取得这个Cookie的名字
		// svalue = sCookie.getValue(); // 取得这个Cookie的内容
		// }

		Cookie cookie = WebUtils.getCookie(request, "user");
		if (cookie == null) {
			return null;
		}
		String json = cookie.getValue();
		ObjectMapper mapper = new ObjectMapper();
		// mapper.enableDefaultTyping();
		// mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
		// Boolean.TRUE);

		User entity = null;

//		ServletContext application = request.getSession().getServletContext();
//		WebApplicationContext springContext = WebApplicationContextUtils
//				.getWebApplicationContext(application);
//		if (springContext == null)
//			return null;
//		UserService userService = (UserService) springContext
//				.getBean("userService");

		try {
			UserCookie mc = mapper.readValue(json, UserCookie.class);
			
			UserService  userService = ToolSpring.getApplicationContext().getBean(UserService.class);

			// UserService service =
			// ApplicationContext.this.getBean(UserService.class);

			entity = userService.get(UUID.fromString(mc.getUuid()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entity;
	}

	/**
	 * 管理员登录信息
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 */
	public void saveMemberCookie(Member entity) {

		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getResponse();

		if (entity == null) {
			Cookie cookie = new Cookie("member", null);
			cookie.setMaxAge(-1);// 保留7天
			response.addCookie(cookie);

			return;
		}

		MemberCookie mc = new MemberCookie();
		mc.setUsername(entity.getUsername());
		mc.setUuid(entity.getId().toString());

		// Cookie cookie = WebUtils.getCookie(request, "login");
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(mc);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Cookie cookie = new Cookie("member", json);
		cookie.setMaxAge(60 * 60 * 24 * 7);// 保留7天
		response.addCookie(cookie);
	}

	/**
	 * cookie中登录
	 * 
	 * @param request
	 * @return
	 */
	public String getMemberIdFromCookie(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, "member");
		if (cookie == null) {
			return null;
		}
		String json = cookie.getValue();
		ObjectMapper mapper = new ObjectMapper();
		// mapper.enableDefaultTyping();
		// mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
		// Boolean.TRUE);
		// Member entity = null;
		// ServletContext application =
		// request.getSession().getServletContext();
		// WebApplicationContext springContext =
		// WebApplicationContextUtils.getWebApplicationContext(application);
		// MemberService memberService = (MemberService)
		// springContext.getBean("memberService");
		try {
			MemberCookie mc = mapper.readValue(json, MemberCookie.class);
			// entity = memberService.get(UUID.fromString(mc.getUuid()));
			return mc.getUuid();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static class UserCookie {
		private String username;
		private String uuid;

		public UserCookie() {

		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

	}

	public static class MemberCookie {
		private String username;
		private String uuid;

		public MemberCookie() {

		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

	}

}
