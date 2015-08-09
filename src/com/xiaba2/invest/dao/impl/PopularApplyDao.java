package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IPopularApplyDao;
import com.xiaba2.invest.domain.PopularApply;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class PopularApplyDao extends AbstractHibernateDao<PopularApply, UUID> implements
IPopularApplyDao {
}