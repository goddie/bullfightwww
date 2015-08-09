package com.xiaba2.invest.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.invest.dao.IPopularApplyDao;
import com.xiaba2.invest.domain.PopularApply;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class PopularApplyService extends BaseService<PopularApply, UUID> {
@Resource
private IPopularApplyDao popularApplyDao;
@Override
protected IBaseDao<PopularApply, UUID> getEntityDao() {
return popularApplyDao;
}
}