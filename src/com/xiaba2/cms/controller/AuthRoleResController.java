package com.xiaba2.cms.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.cms.domain.AuthRoleRes;
import com.xiaba2.cms.service.AuthRoleResService;
@Controller
@RequestMapping("/authroleres")
public class AuthRoleResController {
@Resource
private AuthRoleResService authRoleResService;
}