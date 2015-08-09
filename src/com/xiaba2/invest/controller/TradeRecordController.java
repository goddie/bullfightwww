package com.xiaba2.invest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.invest.domain.Product;
import com.xiaba2.invest.domain.TradeRecord;
import com.xiaba2.invest.domain.User;
import com.xiaba2.invest.service.ProductService;
import com.xiaba2.invest.service.TradeRecordService;
import com.xiaba2.invest.service.UserService;

@RestController
@RequestMapping("/traderecord")
public class TradeRecordController {
	@Resource
	private TradeRecordService tradeRecordService;

	@Resource
	private UserService userService;

	@Resource
	private ProductService productService;

	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(@RequestParam("uid") String uid,
			@RequestParam("pid") String pid, TradeRecord entity,
			HttpServletRequest request) {
		if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(pid)) {
			return new JsonResult("购买失败!");
		}

		User user = userService.get(UUID.fromString(uid));

		UUID productId = UUID.fromString(pid);

		Product product = productService.get(productId);

		entity.setUser(user);
		entity.setProduct(product);
		entity.setCreatedDate(new Date());
		entity.setTitle(product.getName());

		tradeRecordService.save(entity);

		if (entity.getType() == 1) {

			// 增加猫币

			user.setScore(user.getScore() + Math.abs(entity.getAmount()));
			userService.saveOrUpdate(user);

		}

		JsonResult rs = new JsonResult();
		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("购买成功!");
		rs.setData(entity);

		return rs;
	}

	/**
	 * 获取列表
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/json/list")
	public JsonResult jsonList(@RequestParam("p") int p,
			HttpServletRequest request) {

		String uid = request.getParameter("uid");

		User u = null;

		if (!StringUtils.isEmpty(uid)) {
			UUID id = UUID.fromString(uid);
			u = userService.get(id);
		}

		if (u == null) {
			return new JsonResult("没有记录");
		}

		p = Math.max(p, 1);

		Page<TradeRecord> page = new Page<TradeRecord>();
		page.setPageNo(p);
		page.setPageSize(10);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = tradeRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", u.getId()));

		page = tradeRecordService.findPageByCriteria(criteria, page);

		if (page.getResult().isEmpty()) {
			return new JsonResult("没有记录");
		}

		JsonResult rs = new JsonResult();

		rs.setCode(JsonResult.SUCCESS);
		rs.setData(page.getResult());

		return rs;
	}

}