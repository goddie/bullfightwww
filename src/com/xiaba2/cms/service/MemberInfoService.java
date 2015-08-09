package com.xiaba2.cms.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaba2.cms.dao.IMemberInfoDao;
import com.xiaba2.cms.domain.MemberInfo;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class MemberInfoService extends BaseService<MemberInfo, UUID> {

	@Resource
	private IMemberInfoDao memberInfoDao;

	@Override
	protected IBaseDao<MemberInfo, UUID> getEntityDao() {
		return memberInfoDao;
	}

}