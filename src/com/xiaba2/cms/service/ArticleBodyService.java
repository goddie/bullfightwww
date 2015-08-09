package com.xiaba2.cms.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaba2.cms.dao.IArticleBodyDao;
import com.xiaba2.cms.domain.ArticleBody;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;



@Service
public class ArticleBodyService extends BaseService<ArticleBody, UUID>{

	@Resource
	private IArticleBodyDao articleBodyDao;
	
	@Override
	protected IBaseDao<ArticleBody, UUID> getEntityDao() {
		return articleBodyDao;
	}

}
