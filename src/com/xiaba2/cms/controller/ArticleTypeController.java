package com.xiaba2.cms.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.cms.service.ArticleTypeService;
import com.xiaba2.core.JsonResult;

@RestController
@RequestMapping("/articletype")
public class ArticleTypeController {
	@Resource
	private ArticleTypeService articleTypeService;

	/**
	 * 新增
	 * @return
	 */
	@RequestMapping(value = "/admin/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("admin_articletype_add");

		DetachedCriteria criteria = articleTypeService.createDetachedCriteria();
		criteria.add(Restrictions.isNull("parent"));
		criteria.add(Restrictions.eq("isDelete", 0));

		List<ArticleType> list = articleTypeService.findByCriteria(criteria);

		mv.addObject("list", list);

		return mv;
	}

	/**
	 * 管理
	 * @return
	 */
	@RequestMapping(value = "/admin/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("admin_articletype_list");

		DetachedCriteria criteria = articleTypeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		List<ArticleType> list = articleTypeService.findByCriteria(criteria);
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping(value = "/json/list")
	public List<ArticleType> jsonlist(HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		DetachedCriteria criteria = articleTypeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		String pid = request.getParameter("parentId");
		if (!StringUtils.isEmpty(pid)) {
			criteria.add(Restrictions.eq("parent.id", UUID.fromString(pid)));
		} else {
			criteria.add(Restrictions.isNull("parent"));
		}
		List<ArticleType> list = articleTypeService.findByCriteria(criteria);
		rs.setData(list);
		rs.setCode(JsonResult.SUCCESS);
		return list;
	}
	
	
	/**
	 * 新建
	 * 
	 * @param entity
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(ArticleType entity,
			@RequestParam("pid") String pid) {

		ModelAndView mv = new ModelAndView("redirect:/articletype/add");

		if (!pid.equals("0")) {
			ArticleType parent = articleTypeService.get(UUID.fromString(pid));
			entity.setParent(parent);
		}
		entity.setCreatedDate(new Date());
		articleTypeService.save(entity);
		return mv;
	}

}