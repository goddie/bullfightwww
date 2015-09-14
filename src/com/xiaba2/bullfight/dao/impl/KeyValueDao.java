package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IKeyValueDao;
import com.xiaba2.bullfight.domain.KeyValue;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class KeyValueDao extends AbstractHibernateDao<KeyValue, UUID> implements
IKeyValueDao {
}