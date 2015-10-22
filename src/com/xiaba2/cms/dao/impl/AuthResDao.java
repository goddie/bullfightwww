package com.xiaba2.cms.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.cms.dao.IAuthResDao;
import com.xiaba2.cms.domain.AuthRes;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class AuthResDao extends AbstractHibernateDao<AuthRes, UUID> implements
IAuthResDao {
}