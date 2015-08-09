package com.xiaba2.cms.dao.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.xiaba2.cms.dao.IMemberDao;
import com.xiaba2.cms.domain.Member;
import com.xiaba2.core.AbstractHibernateDao;

@Repository
public class MemberDao extends AbstractHibernateDao<Member, UUID> implements IMemberDao{

}
