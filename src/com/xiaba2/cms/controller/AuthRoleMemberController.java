package com.xiaba2.cms.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.cms.domain.AuthRoleMember;
import com.xiaba2.cms.service.AuthRoleMemberService;
@Controller
@RequestMapping("/authrolemember")
public class AuthRoleMemberController {
@Resource
private AuthRoleMemberService authRoleMemberService;
}