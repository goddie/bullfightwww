package com.xiaba2.bullfight.controller;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.bullfight.domain.KeyValue;
import com.xiaba2.bullfight.service.KeyValueService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;

@Controller
@RequestMapping("/keyvalue")
public class KeyValueController {
	@Resource
	private KeyValueService keyValueService;

	@RequestMapping("/admin/add")
	public ModelAndView adminAdd() {
		ModelAndView mv = new ModelAndView("admin_keyvalue_add");

		return mv;
	}

	/**
	 * 列表
	 * 
	 * @return
	 */
	@RequestMapping("/admin/list")
	public ModelAndView adminList() {
		ModelAndView mv = new ModelAndView("admin_keyvalue_list");

		DetachedCriteria criteria = keyValueService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		Page<KeyValue> page = new Page<KeyValue>();
		page.setPageSize(999);
		page.setPageNo(1);

		page = keyValueService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		return mv;
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@RequestMapping("/action/add")
	public ModelAndView actionAdd(KeyValue entity) {
		ModelAndView mv = new ModelAndView("redirect:/keyvalue/admin/add");

		DetachedCriteria criteria = keyValueService.createDetachedCriteria();
		criteria.add(Restrictions.eq("dictKey", entity.getDictKey()));
		criteria.add(Restrictions.eq("isDelete", 0));

		List<KeyValue> list = keyValueService.findByCriteria(criteria);

		if (list != null && list.size() > 0) {
			return mv;
		}

		keyValueService.save(entity);

		return mv;
	}

	/**
	 * 获取值
	 * 
	 * @return
	 */
	@RequestMapping("/json/getbykey")
	public JsonResult jsonGetByKey(@RequestParam("key") String key) {
		JsonResult rs = new JsonResult();
		rs.setCode(JsonResult.SUCCESS);
		rs.setData(0);

		String v = keyValueService.getByKey(key);

		rs.setData(v);
		
		return rs;
	}
}