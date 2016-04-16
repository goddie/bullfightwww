package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IAppVersionDao;
import com.xiaba2.bullfight.domain.AppVersion;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class AppVersionDao extends AbstractHibernateDao<AppVersion, UUID> implements
IAppVersionDao {
}