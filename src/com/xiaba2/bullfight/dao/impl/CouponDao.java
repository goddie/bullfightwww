package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.ICouponDao;
import com.xiaba2.bullfight.domain.Coupon;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class CouponDao extends AbstractHibernateDao<Coupon, UUID> implements
ICouponDao {
}