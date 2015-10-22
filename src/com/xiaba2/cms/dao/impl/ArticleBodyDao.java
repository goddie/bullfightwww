package com.xiaba2.cms.dao.impl;

import java.util.UUID;



import org.springframework.stereotype.Repository;

import com.xiaba2.cms.dao.IArticleBodyDao;
import com.xiaba2.cms.domain.ArticleBody;
import com.xiaba2.core.AbstractHibernateDao;

@Repository
public class ArticleBodyDao extends AbstractHibernateDao<ArticleBody, UUID>
		implements IArticleBodyDao {

}
