package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IMatchDataTeamDao;
import com.xiaba2.bullfight.domain.MatchDataTeam;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class MatchDataTeamDao extends AbstractHibernateDao<MatchDataTeam, UUID> implements
IMatchDataTeamDao {
}