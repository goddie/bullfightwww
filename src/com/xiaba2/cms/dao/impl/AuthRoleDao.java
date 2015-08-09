package com.xiaba2.cms.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.cms.dao.IAuthRoleDao;
import com.xiaba2.cms.domain.AuthRole;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class AuthRoleDao extends AbstractHibernateDao<AuthRole, UUID> implements
IAuthRoleDao {
}