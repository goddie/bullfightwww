package com.xiaba2.bullfight.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xiaba2.bullfight.dao.IKeyValueDao;
import com.xiaba2.bullfight.domain.KeyValue;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
import com.xiaba2.core.JsonResult;

@Service
public class KeyValueService extends BaseService<KeyValue, UUID> {
	@Resource
	private IKeyValueDao keyValueDao;

	@Override
	protected IBaseDao<KeyValue, UUID> getEntityDao() {
		return keyValueDao;
	}
	
	
	/**
	 * 取值
	 * @param key
	 * @return
	 */
	@Transactional
	public String getByKey(String key)
	{

		String v = "";
		
		DetachedCriteria criteria = keyValueDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("dictKey", key));
		criteria.add(Restrictions.eq("isDelete", 0));

		List<KeyValue> list = keyValueDao.findByCriteria(criteria);

		if (list != null && list.size() > 0) {
			v = list.get(0).getDictValue();
		}
		
		
		return v;
	}
	
	
	
}