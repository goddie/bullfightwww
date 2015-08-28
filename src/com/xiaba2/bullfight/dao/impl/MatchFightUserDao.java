package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IMatchFightUserDao;
import com.xiaba2.bullfight.domain.MatchFightUser;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class MatchFightUserDao extends AbstractHibernateDao<MatchFightUser, UUID> implements
IMatchFightUserDao {
}