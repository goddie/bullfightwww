package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.ISysConfigDao;
import com.xiaba2.bullfight.domain.SysConfig;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class SysConfigDao extends AbstractHibernateDao<SysConfig, UUID> implements
ISysConfigDao {
}