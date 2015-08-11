package com.xiaba2.bullfight.service;

import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.xiaba2.bullfight.dao.ICouponDao;
import com.xiaba2.bullfight.domain.Coupon;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class CouponService extends BaseService<Coupon, UUID> {
	@Resource
	private ICouponDao couponDao;

	@Override
	protected IBaseDao<Coupon, UUID> getEntityDao() {
		return couponDao;
	}
	
	@Transactional
	public Boolean useCoupon(String code)
	{
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("code", code));
		
		return false;
	}
}