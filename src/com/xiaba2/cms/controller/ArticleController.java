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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.ArticleBody;
import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.cms.domain.User;
import com.xiaba2.cms.service.ArticleBodyService;
import com.xiaba2.cms.service.ArticleService;
import com.xiaba2.cms.service.ArticleTypeService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/article")
public class ArticleController {
	@Resource
	private ArticleService articleService;

	@Resource
	private ArticleBodyService articleBodyService;
	
	@Resource
	private ArticleTypeService articleTypeService;
	
	

	/**
	 * 新增
	 * @return
	 */
	@RequestMapping(value = "/admin/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("admin_article_add");
		return mv;
	}
	
	
	/**
	 * 管理
	 * @return
	 */
	@RequestMapping(value = "/admin/list")
	public ModelAndView list(@RequestParam("p") int p, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_article_list");
		 
		
		Page<Article> page = new Page<Article>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = articleService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		HttpUtil.addSearchLike(criteria, mv, request, "title");
		
		page = articleService.findPageByCriteria(criteria, page);

 
		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml",page.genPageHtml(request));
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
	public ModelAndView add(Article entity,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/article/admin/add");
		entity.setCreatedDate(new Date());
		
		String pid = request.getParameter("parentTypeId");
		if (!StringUtils.isEmpty(pid)) {
			entity.setType(articleTypeService.get(UUID.fromString(pid)));
		}
		
		
		String sid = request.getParameter("subTypeId");
		if (!StringUtils.isEmpty(sid)) {
			entity.setSubType(articleTypeService.get(UUID.fromString(sid)));
		}
		
		
		articleService.save(entity);
		//entity.setAuthor(author);
		ArticleBody body = new ArticleBody();
		
		


		body.setBody(request.getParameter("content"));
		body.setArticle(entity);
		body.setCreatedDate(new Date());
		
		articleBodyService.save(body);
		
		
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
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("type") int type, @RequestParam("p") int p, HttpServletRequest request) {

		JsonResult rs = new JsonResult();

//		String title = request.getParameter("title");
		
		UUID typeId = null;
		
		if(type==1)
		{
			typeId = UUID.fromString("17bdde54-7251-497f-b14c-67ddc3f352c9");
		}
		
		if(type==2)
		{
			typeId = UUID.fromString("452ce559-70c4-4bc3-8916-4de25a868dbd");
		}
		
		if(type==3)
		{
			typeId = UUID.fromString("0a1e9676-ec5e-4335-9e2a-57083bf9ba05");
		}

		
		ArticleType articleType = articleTypeService.get(typeId);
		
		DetachedCriteria criteria = articleService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("subType", articleType));


		Page<Article> page = new Page<Article>();
		page.setPageNo(p);
		page.setPageSize(15);
		page.addOrder("createdDate", "desc");
		
		page = articleService.findPageByCriteria(criteria, page);
		
		 
		rs.setData(page.getResult());
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
	
	

	/**
	 * 根据标题获取内容
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("id") UUID uuid, HttpServletRequest request) {
		ModelAndView mv =new ModelAndView("redirect:/article/admin/list?p=1");

		Article article = articleService.get(uuid);

		article.setIsDelete(1);
		articleService.saveOrUpdate(article);
		
		return mv;
	}
	
	/**
	 * 根据标题获取内容
	 * 
	 * @return
	 */
	@RequestMapping(value = "/page/newsdetail")
	public ModelAndView pageNewsDetail(@RequestParam("uuid") UUID uuid, HttpServletRequest request) {
		ModelAndView mv =new ModelAndView("news_detail");

		Article article = articleService.get(uuid);

		DetachedCriteria criteria2 = articleBodyService
				.createDetachedCriteria();
		criteria2.add(Restrictions.eq("isDelete", 0));
		criteria2.add(Restrictions.eq("article", article));

		List<ArticleBody> list2 = articleBodyService.findByCriteria(criteria2);
		if (list2 == null || list2.size() == 0) {
			return mv;
		}
		mv.addObject("article",article);
		mv.addObject("body",list2.get(0));
		
		
		return mv;
	}
}
