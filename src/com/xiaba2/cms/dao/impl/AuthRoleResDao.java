package com.xiaba2.cms.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.cms.dao.IAuthRoleResDao;
import com.xiaba2.cms.domain.AuthRoleRes;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class AuthRoleResDao extends AbstractHibernateDao<AuthRoleRes, UUID> implements
IAuthRoleResDao {
}