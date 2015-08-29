package com.xiaba2.bullfight.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.PayRecord;
import com.xiaba2.bullfight.domain.SysConfig;
import com.xiaba2.bullfight.service.SysConfigService;
import com.xiaba2.core.JsonResult;

@Controller
@RequestMapping("/sysconfig")
public class SysConfigController {
	@Resource
	private SysConfigService sysConfigService;
	
	@RequestMapping("/admin/add")
	public ModelAndView adminAdd() 		
	{
		ModelAndView mv = new ModelAndView("admin_sysconfig_add");
		
		return mv;
	}
}