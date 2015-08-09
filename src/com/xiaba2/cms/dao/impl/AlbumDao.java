package com.xiaba2.cms.dao.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.xiaba2.cms.dao.IAlbumDao;
import com.xiaba2.cms.domain.Album;
import com.xiaba2.core.AbstractHibernateDao;

@Repository
public class AlbumDao extends AbstractHibernateDao<Album, UUID> implements
		IAlbumDao {



}
