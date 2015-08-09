package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IProductInfoDao;
import com.xiaba2.invest.domain.ProductInfo;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class ProductInfoDao extends AbstractHibernateDao<ProductInfo, UUID> implements
IProductInfoDao {
}