package com.xiaba2.cms.dao.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.xiaba2.cms.dao.IMemberTypeDao;
import com.xiaba2.cms.domain.MemberType;
import com.xiaba2.core.AbstractHibernateDao;


@Repository
public class MemberTypeDao extends AbstractHibernateDao<MemberType, UUID>
		implements IMemberTypeDao {

}
