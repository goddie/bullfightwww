package com.xiaba2.cms.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.cms.domain.AuthGroupMember;
import com.xiaba2.cms.service.AuthGroupMemberService;
@Controller
@RequestMapping("/authgroupmember")
public class AuthGroupMemberController {
@Resource
private AuthGroupMemberService authGroupMemberService;
}