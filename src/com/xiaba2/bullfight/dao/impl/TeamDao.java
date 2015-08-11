package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.ITeamDao;
import com.xiaba2.bullfight.domain.Team;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class TeamDao extends AbstractHibernateDao<Team, UUID> implements
ITeamDao {
}