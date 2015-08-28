package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.ISysConfigDao;
import com.xiaba2.bullfight.domain.SysConfig;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class SysConfigService extends BaseService<SysConfig, UUID> {
@Resource
private ISysConfigDao sysConfigDao;
@Override
protected IBaseDao<SysConfig, UUID> getEntityDao() {
return sysConfigDao;
}
}