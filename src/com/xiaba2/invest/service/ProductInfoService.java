package com.xiaba2.invest.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.invest.dao.IProductInfoDao;
import com.xiaba2.invest.domain.ProductInfo;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class ProductInfoService extends BaseService<ProductInfo, UUID> {
@Resource
private IProductInfoDao productInfoDao;
@Override
protected IBaseDao<ProductInfo, UUID> getEntityDao() {
return productInfoDao;
}
}