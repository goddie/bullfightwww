package com.xiaba2.cms.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.cms.dao.IAuthRoleMemberDao;
import com.xiaba2.cms.domain.AuthRoleMember;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class AuthRoleMemberDao extends AbstractHibernateDao<AuthRoleMember, UUID> implements
IAuthRoleMemberDao {
}