package com.xiaba2.invest.service;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaba2.invest.dao.IOrganizationDao;
import com.xiaba2.invest.domain.Organization;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;
@Service
public class OrganizationService extends BaseService<Organization, UUID> {
@Resource
private IOrganizationDao organizationDao;
@Override
protected IBaseDao<Organization, UUID> getEntityDao() {
return organizationDao;
}
}