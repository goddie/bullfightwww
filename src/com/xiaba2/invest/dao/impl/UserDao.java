package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IUserDao;
import com.xiaba2.invest.domain.User;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class UserDao extends AbstractHibernateDao<User, UUID> implements
IUserDao {
}