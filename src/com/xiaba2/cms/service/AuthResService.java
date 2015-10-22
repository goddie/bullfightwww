package com.xiaba2.cms.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.cms.dao.IAuthResDao;
import com.xiaba2.cms.domain.AuthRes;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class AuthResService extends BaseService<AuthRes, UUID> {
@Resource
private IAuthResDao authResDao;
@Override
protected IBaseDao<AuthRes, UUID> getEntityDao() {
return authResDao;
}
}