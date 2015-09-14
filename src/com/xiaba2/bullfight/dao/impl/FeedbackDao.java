package com.xiaba2.bullfight.dao.impl;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xiaba2.bullfight.dao.IFeedbackDao;
import com.xiaba2.bullfight.domain.Feedback;
import com.xiaba2.core.AbstractHibernateDao;
@Repository
public class FeedbackDao extends AbstractHibernateDao<Feedback, UUID> implements
IFeedbackDao {
}