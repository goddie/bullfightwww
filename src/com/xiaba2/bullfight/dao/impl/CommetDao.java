package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.ICommetDao;
import com.xiaba2.bullfight.domain.Commet;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class CommetDao extends AbstractHibernateDao<Commet, UUID> implements
ICommetDao {
}