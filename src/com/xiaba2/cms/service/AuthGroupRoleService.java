package com.xiaba2.cms.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.cms.dao.IAuthGroupRoleDao;
import com.xiaba2.cms.domain.AuthGroupRole;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class AuthGroupRoleService extends BaseService<AuthGroupRole, UUID> {
@Resource
private IAuthGroupRoleDao authGroupRoleDao;
@Override
protected IBaseDao<AuthGroupRole, UUID> getEntityDao() {
return authGroupRoleDao;
}
}