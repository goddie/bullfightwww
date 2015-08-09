package com.xiaba2.util;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import com.xiaba2.cms.domain.Member;
import com.xiaba2.cms.service.MemberService;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.UserService;

@Component
public class SessionUtil {
	
	
//	@Resource
//	private HttpSession session;
	
//	@Resource
//	private HttpServletRequest request;

	private static SessionUtil instance;

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

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();

		Object sess = WebUtils.getSessionAttribute(request, "user");
		if (sess == null) {
			return null;
		}

		User u = (User) sess;
		UUID id = u.getId();

//		User fresh = SessionUtil.getInstance().userService.get(id);

		return u;

	}

	/**
	 * 登录的member
	 * 
	 * @return
	 */
	public Member getSessionMember() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();

		Object sess = WebUtils.getSessionAttribute(request, "member");
		if (sess == null) {
			return null;
		}

		Member m = (Member) sess;
		UUID id = m.getId();

//		Member fresh = SessionUtil.getInstance().memberService.get(id);

		return m;

	}
}
