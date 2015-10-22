package com.xiaba2.cms.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.cms.dao.IAuthRoleResDao;
import com.xiaba2.cms.domain.AuthRoleRes;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class AuthRoleResService extends BaseService<AuthRoleRes, UUID> {
@Resource
private IAuthRoleResDao authRoleResDao;
@Override
protected IBaseDao<AuthRoleRes, UUID> getEntityDao() {
return authRoleResDao;
}
}