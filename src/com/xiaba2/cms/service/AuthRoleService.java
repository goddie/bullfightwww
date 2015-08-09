package com.xiaba2.cms.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.cms.dao.IAuthRoleDao;
import com.xiaba2.cms.domain.AuthRole;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class AuthRoleService extends BaseService<AuthRole, UUID> {
@Resource
private IAuthRoleDao authRoleDao;
@Override
protected IBaseDao<AuthRole, UUID> getEntityDao() {
return authRoleDao;
}
}