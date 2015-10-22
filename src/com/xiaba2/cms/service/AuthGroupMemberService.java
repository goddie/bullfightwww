package com.xiaba2.cms.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.cms.dao.IAuthGroupMemberDao;
import com.xiaba2.cms.domain.AuthGroupMember;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class AuthGroupMemberService extends BaseService<AuthGroupMember, UUID> {
@Resource
private IAuthGroupMemberDao authGroupMemberDao;
@Override
protected IBaseDao<AuthGroupMember, UUID> getEntityDao() {
return authGroupMemberDao;
}
}