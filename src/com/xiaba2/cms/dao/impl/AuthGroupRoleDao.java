package com.xiaba2.cms.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.cms.dao.IAuthGroupRoleDao;
import com.xiaba2.cms.domain.AuthGroupRole;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class AuthGroupRoleDao extends AbstractHibernateDao<AuthGroupRole, UUID> implements
IAuthGroupRoleDao {
}