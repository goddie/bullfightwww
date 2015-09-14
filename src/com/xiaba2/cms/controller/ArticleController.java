package com.xiaba2.cms.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.ArticleBody;
import com.xiaba2.cms.service.ArticleBodyService;
import com.xiaba2.cms.service.ArticleService;
import com.xiaba2.core.JsonResult;

@RestController
@RequestMapping("/article")
public class ArticleController {
	@Resource
	private ArticleService articleService;

	@Resource
	private ArticleBodyService articleBodyService;

	@RequestMapping(value = "/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("admin_article_add");
		return mv;
	}

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView hello2() {

		ArticleBody entity = new ArticleBody();

		ModelAndView mv = new ModelAndView();
		mv.addObject("message", entity.getId().toString());
		mv.setViewName("users");
		return mv;
	}

	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(Article entity, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("redirect:/article/add");

		entity.setCreatedDate(new Date());

		articleService.save(entity);

		ArticleBody articleBody = new ArticleBody();
		articleBody.setArticle(entity);
		articleBody.setBody(request.getParameter("content"));
		articleBody.setCreatedDate(new Date());

		articleBodyService.save(articleBody);

		return mv;

	}

	/**
	 * 根据标题获取内容
	 * 
	 * @return
	 */
	@RequestMapping(value = "/json/content")
	public JsonResult jsonContent(@RequestParam("title") String title, HttpServletRequest request) {

		JsonResult rs = new JsonResult();

//		String title = request.getParameter("title");

		DetachedCriteria criteria = articleService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("title", title));

		List<Article> list = articleService.findByCriteria(criteria);

		if (list == null || list.size() == 0) {
			return rs;
		}

		DetachedCriteria criteria2 = articleBodyService
				.createDetachedCriteria();
		criteria2.add(Restrictions.eq("isDelete", 0));
		criteria2.add(Restrictions.eq("article", list.get(0)));

		List<ArticleBody> list2 = articleBodyService.findByCriteria(criteria2);
		if (list2 == null || list2.size() == 0) {
			return rs;
		}

		rs.setData(list2.get(0).getBody());
		rs.setCode(JsonResult.SUCCESS);

		return rs;
	}
	
	
	/**
	 * 根据标题获取内容
	 * 
	 * @return
	 */
	@RequestMapping(value = "/page/detail")
	public ModelAndView pageDetail(@RequestParam("title") String title, HttpServletRequest request) {
		ModelAndView mv =new ModelAndView("article_detail");
		
		
		DetachedCriteria criteria = articleService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("title", title));

		List<Article> list = articleService.findByCriteria(criteria);

		if (list == null || list.size() == 0) {
			return mv;
		}

		DetachedCriteria criteria2 = articleBodyService
				.createDetachedCriteria();
		criteria2.add(Restrictions.eq("isDelete", 0));
		criteria2.add(Restrictions.eq("article", list.get(0)));

		List<ArticleBody> list2 = articleBodyService.findByCriteria(criteria2);
		if (list2 == null || list2.size() == 0) {
			return mv;
		}

		mv.addObject("body",list2.get(0));
		
		
		return mv;
	}
}
