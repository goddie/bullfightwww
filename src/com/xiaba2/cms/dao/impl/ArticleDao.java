package com.xiaba2.cms.dao.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.xiaba2.cms.dao.IArticleDao;
import com.xiaba2.cms.domain.Article;
import com.xiaba2.core.AbstractHibernateDao;

@Repository
public class ArticleDao extends AbstractHibernateDao<Article, UUID> implements
		IArticleDao {

}
