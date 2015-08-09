package com.xiaba2.cms.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaba2.cms.dao.IArticleDao;
import com.xiaba2.cms.domain.Article;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class ArticleService extends BaseService<Article, UUID> {

	@Resource
	private IArticleDao articleDao;

	@Override
	protected IBaseDao<Article, UUID> getEntityDao() {
		return articleDao;
	}

}
