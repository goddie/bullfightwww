package com.xiaba2.cms.dao.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.xiaba2.cms.dao.IArticleTypeDao;
import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.core.AbstractHibernateDao;

@Repository
public class ArticleTypeDao extends AbstractHibernateDao<ArticleType, UUID>
		implements IArticleTypeDao {

}
