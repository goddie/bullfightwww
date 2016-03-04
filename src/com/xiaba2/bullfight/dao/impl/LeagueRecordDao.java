package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.ILeagueRecordDao;
import com.xiaba2.bullfight.domain.LeagueRecord;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class LeagueRecordDao extends AbstractHibernateDao<LeagueRecord, UUID> implements
ILeagueRecordDao {
}