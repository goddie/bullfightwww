package com.xiaba2.cms.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaba2.cms.dao.IMemberTypeDao;
import com.xiaba2.cms.domain.MemberType;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class MemberTypeService extends BaseService<MemberType, UUID> {

	@Resource
	private IMemberTypeDao memberTypeDao;

	@Override
	protected IBaseDao<MemberType, UUID> getEntityDao() {
		return memberTypeDao;
	}

}