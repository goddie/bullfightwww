package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.ITeamUserDao;
import com.xiaba2.bullfight.domain.TeamUser;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class TeamUserDao extends AbstractHibernateDao<TeamUser, UUID> implements
ITeamUserDao {
}