package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IFollowDao;
import com.xiaba2.bullfight.domain.Follow;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class FollowDao extends AbstractHibernateDao<Follow, UUID> implements
IFollowDao {
}