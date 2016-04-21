package com.xiaba2.bullfight.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.bullfight.domain.AppVersion;
import com.xiaba2.bullfight.service.AppVersionService;
import com.xiaba2.cms.domain.User;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;

@RestController
@RequestMapping("/appversion")
public class AppVersionController {
	@Resource
	private AppVersionService appVersionService;
	
	
	@RequestMapping("/admin/add")
	public ModelAndView adminAdd()
	{
		return new ModelAndView("admin_appversion_add");	
	}
	
	
	@RequestMapping("/action/add")
	public ModelAndView actionAdd(AppVersion entity,HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("admin_appversion_add");
		
		if(StringUtils.isEmpty(entity.getApkUrl()))
		{
			mv.addObject("msg","添加失败");
			return mv;
		}
		
		entity.setCreatedDate(new Date());
		
		appVersionService.save(entity);
		
		mv.addObject("msg","添加成功");
		
		return mv;
	}
	
	
	@RequestMapping("/json/getlast")
	public JsonResult jsonGetLast(HttpServletRequest request) {

		JsonResult rs = new JsonResult();

		Page<AppVersion> page = new Page<AppVersion>();
		
		page.setPageSize(1);
		page.setPageNo(1);
		
		DetachedCriteria criteria = appVersionService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.addOrder(Order.desc("versionCode"));
		
		page = appVersionService.findPageByCriteria(criteria, page);
		
		rs.setData(page.getResult());
		
		if(page.getResult().size()==0)
		{
			rs.setCode(JsonResult.FAIL);
		}else
		{
			rs.setCode(JsonResult.SUCCESS);
			rs.setMsg("版本升级");
		}
		

		

		return rs;
	}
	
}