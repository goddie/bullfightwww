package com.xiaba2.cms.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.cms.dao.IAuthGroupDao;
import com.xiaba2.cms.domain.AuthGroup;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class AuthGroupService extends BaseService<AuthGroup, UUID> {
@Resource
private IAuthGroupDao authGroupDao;
@Override
protected IBaseDao<AuthGroup, UUID> getEntityDao() {
return authGroupDao;
}
}