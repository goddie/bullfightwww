package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IFollowDao;
import com.xiaba2.invest.domain.Follow;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class FollowDao extends AbstractHibernateDao<Follow, UUID> implements
IFollowDao {
}