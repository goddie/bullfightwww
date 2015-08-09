package com.xiaba2.invest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.invest.domain.Product;
import com.xiaba2.invest.domain.ProductInfo;
import com.xiaba2.invest.domain.TradeRecord;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.ProductInfoService;
import com.xiaba2.invest.service.ProductService;
import com.xiaba2.invest.service.TradeRecordService;
import com.xiaba2.invest.service.UserService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Resource
	private ProductService productService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private TradeRecordService tradeRecordService;
	
	@Resource
	private ProductInfoService productInfoService;
	
	/**
	 *  
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("admin_product_" + name);
	}
	
	
	 
	/**
	 * 购买须知
	 * @param pid
	 * @param type 1=购买须知 2=产品详情
	 * @return
	 */
	@RequestMapping(value = "/page/notice")
	public ModelAndView getNotice(@RequestParam("pid") String pid,@RequestParam("type") int type) {
				
		ModelAndView mv= new ModelAndView("product_notice");
		
		UUID id = UUID.fromString(pid);
		
		Product entity = productService.get(id);

		if (entity == null) {
			return mv;
		}
		
		
		DetachedCriteria criteria = productInfoService.createDetachedCriteria();
		criteria.add(Restrictions.eq("type", type));
		criteria.add(Restrictions.eq("product", entity));
		
		
		List<ProductInfo> list =productInfoService.findByCriteria(criteria);
		
		mv.addObject("list", list);
		
		return mv;
	}
	

	
	@RequestMapping(value = "/admin/list")
	public ModelAndView getList() {
		
		List<Product> list = productService.loadAll();
		
		ModelAndView mv= new ModelAndView("admin_product_list");
		mv.addObject("list", list);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/add")
	public ModelAndView add(Product entity)
	{
		entity.setCreatedDate(new Date());
		entity.setPubDate(new Date());
		
		productService.save(entity);
		
		return new ModelAndView("redirect:/product/admin/add");
	}
	
	
	/**
	 * 获取列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("type") int type, @RequestParam("p") int p) {
		p = Math.max(p, 1);

		Page<Product> page = new Page<Product>();
		page.setPageNo(p);
		page.setPageSize(10);
		page.addOrder("createdDate", "desc");
		

		DetachedCriteria criteria = productService.createDetachedCriteria();
		
		//推荐
		if (type==0) {
			//criteria.add(Restrictions.eq("risk",""))
		}
		
		//低风险
		if (type==1) {
			criteria.add(Restrictions.eq("risk","低风险"));
		}
		
		//高风险
		if (type==2) {
			criteria.add(Restrictions.eq("risk","高风险"));
		}
		
		page = productService.findPageByCriteria(criteria, page);

		
		if (page.getResult().isEmpty()) {
			return new JsonResult();
		}
		
		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}
	
	
	/**
	 * 具体产品信息
	 * 
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/json/detail")
	public JsonResult jsonDetail(@RequestParam("uuid") String uuid) {
		
		UUID id = UUID.fromString(uuid);
		
		Product entity = productService.get(id);

		if (entity == null) {
			return new JsonResult();
		}
		
		JsonResult rs=new JsonResult();
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(entity);
		
		return rs;
	}
	
	/**
	 * 获取列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/listuser")
	public JsonResult jsonListUser(@RequestParam("uuid") String uuid,@RequestParam("p") int p) {
		
		User user = userService.getByMemberUUID(uuid);
		
		p = Math.max(p, 1);

		Page<TradeRecord> page = new Page<TradeRecord>();
		page.setPageNo(p);
		page.setPageSize(10);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = tradeRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", user.getId()));
		
		page = tradeRecordService.findPageByCriteria(criteria, page);

		List<Product> list= new ArrayList<Product>();
		
		for (TradeRecord record : page.getResult()) {
			list.add(record.getProduct());	
		}
		
		
		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(list);

		return rs;
	}
	
	
	
	
}