package com.xiaba2.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.cms.domain.ArticleBody;
import com.xiaba2.cms.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Resource
	private ArticleService articleService;

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView hello2() {
		
		ArticleBody entity = new ArticleBody();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", entity.getId().toString());
		mv.setViewName("users");
		return mv;
	}

}
