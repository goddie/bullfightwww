package com.xiaba2.bullfight.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.bullfight.dao.IAppVersionDao;
import com.xiaba2.bullfight.domain.AppVersion;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class AppVersionService extends BaseService<AppVersion, UUID> {
@Resource
private IAppVersionDao appVersionDao;
@Override
protected IBaseDao<AppVersion, UUID> getEntityDao() {
return appVersionDao;
}
}