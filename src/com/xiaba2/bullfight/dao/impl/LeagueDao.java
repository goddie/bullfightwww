package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.ILeagueDao;
import com.xiaba2.bullfight.domain.League;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class LeagueDao extends AbstractHibernateDao<League, UUID> implements
ILeagueDao {
}