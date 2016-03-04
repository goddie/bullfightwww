package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.ILeagueTeamDao;
import com.xiaba2.bullfight.domain.LeagueTeam;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class LeagueTeamDao extends AbstractHibernateDao<LeagueTeam, UUID> implements
ILeagueTeamDao {
}