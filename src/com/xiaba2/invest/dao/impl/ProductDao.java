package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IProductDao;
import com.xiaba2.invest.domain.Product;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class ProductDao extends AbstractHibernateDao<Product, UUID> implements
IProductDao {
}