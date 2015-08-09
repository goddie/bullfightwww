package com.xiaba2.invest.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.invest.dao.IOrganizationDao;
import com.xiaba2.invest.domain.Organization;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class OrganizationDao extends AbstractHibernateDao<Organization, UUID> implements
IOrganizationDao {
}