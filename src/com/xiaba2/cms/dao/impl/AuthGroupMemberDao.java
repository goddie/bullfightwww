package com.xiaba2.cms.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.cms.dao.IAuthGroupMemberDao;
import com.xiaba2.cms.domain.AuthGroupMember;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class AuthGroupMemberDao extends AbstractHibernateDao<AuthGroupMember, UUID> implements
IAuthGroupMemberDao {
}