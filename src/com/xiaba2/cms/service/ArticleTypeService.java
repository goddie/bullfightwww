package com.xiaba2.cms.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaba2.cms.dao.IArticleTypeDao;
import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class ArticleTypeService extends BaseService<ArticleType, UUID>{

	@Resource
	private IArticleTypeDao articleTypeDao;
	
	@Override
	protected IBaseDao<ArticleType, UUID> getEntityDao() {
		return articleTypeDao;
	}

}