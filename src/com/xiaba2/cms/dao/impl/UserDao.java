package com.xiaba2.cms.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.cms.dao.IUserDao;
import com.xiaba2.cms.domain.User;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class UserDao extends AbstractHibernateDao<User, UUID> implements
IUserDao {
}