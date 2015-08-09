package com.xiaba2.cms.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.cms.dao.IAuthRoleMemberDao;
import com.xiaba2.cms.domain.AuthRoleMember;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class AuthRoleMemberService extends BaseService<AuthRoleMember, UUID> {
@Resource
private IAuthRoleMemberDao authRoleMemberDao;
@Override
protected IBaseDao<AuthRoleMember, UUID> getEntityDao() {
return authRoleMemberDao;
}
}