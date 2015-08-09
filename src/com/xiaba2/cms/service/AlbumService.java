package com.xiaba2.cms.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaba2.cms.dao.IAlbumDao;
import com.xiaba2.cms.domain.Album;
import com.xiaba2.core.BaseService;
import com.xiaba2.core.IBaseDao;

@Service
public class AlbumService extends BaseService<Album, UUID> {

	@Resource
	private IAlbumDao albumDao;
	
	
	@Override
	protected IBaseDao<Album, UUID> getEntityDao() {
		return albumDao;
	}

}
