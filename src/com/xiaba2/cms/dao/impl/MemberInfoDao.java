package com.xiaba2.cms.dao.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.xiaba2.cms.dao.IMemberInfoDao;
import com.xiaba2.cms.domain.MemberInfo;
import com.xiaba2.core.AbstractHibernateDao;

@Repository
public class MemberInfoDao extends AbstractHibernateDao<MemberInfo, UUID> implements IMemberInfoDao{

}
