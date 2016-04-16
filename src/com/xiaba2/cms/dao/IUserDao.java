package com.xiaba2.cms.dao;
import java.util.UUID;
import com.xiaba2.cms.domain.User;
import com.xiaba2.core.IBaseDao;
public interface IUserDao extends IBaseDao<User, UUID> {
	
	void updateData(User user);
}