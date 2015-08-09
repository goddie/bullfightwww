package com.xiaba2.invest.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.invest.domain.Product;
import com.xiaba2.invest.domain.ProductInfo;
import com.xiaba2.invest.service.ProductInfoService;
import com.xiaba2.invest.service.ProductService;

@RestController
@RequestMapping("/productinfo")
public class ProductInfoController {
	@Resource
	private ProductInfoService productInfoService;

	@Resource
	private ProductService productService;

	/**
	 *  
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_productinfo_" + name);
	}
	
	
	
 
	@RequestMapping(value = "/add")
	public ModelAndView add(@RequestParam("pid") String pid,ProductInfo entity)
	{
		ModelAndView mv = new ModelAndView("forward:admin/add");
		
		Product p = productService.get(UUID.fromString(pid));
		
		entity.setCreatedDate(new Date());
		entity.setProduct(p);
		
		
		productInfoService.save(entity);
		
		
		return mv;
	}
	
 
	@RequestMapping(value = "/admin/add")
	public ModelAndView pageAdd(@RequestParam("pid") String pid,@RequestParam("type") int type)
	{
		ModelAndView mv = new ModelAndView("admin_productinfo_add");
		Product p = productService.get(UUID.fromString(pid));
		
		mv.addObject("product",p);
		mv.addObject("type",type);
		
		
		DetachedCriteria criteria = productInfoService.createDetachedCriteria();
		
		criteria.add(Restrictions.eq("product",p));
		criteria.add(Restrictions.eq("type",type));
		
		List<ProductInfo> list=  productInfoService.findByCriteria(criteria);
		
		mv.addObject("list",list);
		
		return mv;
	}
	
}