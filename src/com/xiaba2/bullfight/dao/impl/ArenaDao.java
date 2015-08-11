package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IArenaDao;
import com.xiaba2.bullfight.domain.Arena;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class ArenaDao extends AbstractHibernateDao<Arena, UUID> implements
IArenaDao {
}