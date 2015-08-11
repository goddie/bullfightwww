package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IMatchDataUserDao;
import com.xiaba2.bullfight.domain.MatchDataUser;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class MatchDataUserDao extends AbstractHibernateDao<MatchDataUser, UUID> implements
IMatchDataUserDao {
}