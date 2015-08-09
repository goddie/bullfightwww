package com.xiaba2.cms.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.cms.dao.IAuthGroupDao;
import com.xiaba2.cms.domain.AuthGroup;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class AuthGroupDao extends AbstractHibernateDao<AuthGroup, UUID> implements
IAuthGroupDao {
}