package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IMatchFightDao;
import com.xiaba2.bullfight.domain.MatchFight;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class MatchFightDao extends AbstractHibernateDao<MatchFight, UUID> implements
IMatchFightDao {
}