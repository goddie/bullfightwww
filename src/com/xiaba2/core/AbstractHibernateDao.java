package com.xiaba2.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

public abstract class AbstractHibernateDao<T, ID extends Serializable>
		implements IBaseDao<T, ID> {

	Logger logger = Logger.getLogger(AbstractHibernateDao.class.getName());

	@Resource
	protected SessionFactory sessionFactory;

	/**
	 * 为E对应的实例类型
	 */
	private Class<?> entityClass;

	/**
	 * 获取E实例类的类型
	 */
	public AbstractHibernateDao() {
		// 通过反射取得Entity的Class.
		try {
			Object genericClz = getClass().getGenericSuperclass();
			if (genericClz instanceof ParameterizedType) {
				entityClass = (Class<?>) ((ParameterizedType) getClass()
						.getGenericSuperclass()).getActualTypeArguments()[0];
			}
		} catch (Exception T) {
			logger.log(Level.SEVERE, "error detail:", T);
		}
	}

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Integer bulkUpdate(String hql) {

		Query query = getSession().createQuery(hql);

		return query.getFirstResult();

	}

	public Integer bulkUpdate(String hql, Object... values) {

		Query query = getSession().createQuery(hql);

		for (int i = 0; i < values.length; i++) {
			Object object = values[i];
			query.setParameter(i, object);
		}

		return query.getFirstResult();
	}

	public Criteria createCriteria() {
		return this.createDetachedCriteria().getExecutableCriteria(
				this.getSession());
	}

	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.entityClass);
	}

	public DetachedCriteria createDetachedCriteria(
			Class<? extends Serializable> c) {
		return DetachedCriteria.forClass(c);
	}

	public void delete(Collection<T> entitys) {

		for (T entity : entitys) {
			getSession().delete(entity);
		}

	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void delete(T entity, LockOptions lock) {

		getSession().buildLockRequest(lock).lock(entity);
		getSession().delete(entity);
		// 如果实体不为null,立即刷新,否则锁不会生效
		this.flush();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql) {

		return getSession().createQuery(hql).list();

	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... values) {

		Query q = getSession().createQuery(hql);

		for (int i = 0; i < values.length; i++) {
			Object object = values[i];
			q.setParameter(i, object);
		}

		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria) {
		Criteria criteriaExecute = criteria.getExecutableCriteria(getSession());
		return criteriaExecute.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria,
			Integer firstResult, Integer maxResults) {

		Criteria criteriaExecute = criteria.getExecutableCriteria(getSession());
		criteriaExecute.setFirstResult(firstResult);
		criteriaExecute.setMaxResults(maxResults);

		return criteriaExecute.list();
	}

	@SuppressWarnings("unchecked")
	public Page<T> findPageByCriteria(DetachedCriteria criteria, Page<T> page) {

		Integer firstResult = (page.getPageNo() - 1) * page.getPageSize();
		Integer maxResults = page.getPageSize();

		Criteria criteriaExecute = criteria.getExecutableCriteria(getSession());
		criteriaExecute.setFirstResult(firstResult);
		criteriaExecute.setMaxResults(maxResults);

		for (String[] strs : page.getOrders()) {
			if (strs[1].toLowerCase().equals("desc")) {
				criteriaExecute.addOrder(Order.desc(strs[0]));
			} else {
				criteriaExecute.addOrder(Order.asc(strs[0]));
			}

		}

		List<T> list = criteriaExecute.list();

		Criteria criteriaCount = criteria.getExecutableCriteria(getSession());

		Object rs = criteriaCount.setProjection(Projections.rowCount())
				.uniqueResult();
		long rowCount = rs == null ? 0 : (Long) rs;
		page.setResult(list);
		page.setTotalCount(rowCount);

		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findByNativeSQL(String sql) {

		Query query = getSession().createSQLQuery(sql);

		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		List<Map<String, Object>> list = query.list();

		return list;
	}

	public void flush() {
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	public T get(ID id) {
		return (T) getSession().get(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public T get(ID id, LockOptions lock) {
		T entity = (T) getSession().get(this.entityClass, id, lock);
		if (entity != null) {
			this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
		}
		return entity;
	}

	public Integer getRowCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		return (Integer) this.findByCriteria(criteria, 0, 1).get(0);
	}

	/**
	 * 
	 * 该枚举类型用于,getStackValue方法中的Stack枚举
	 * 
	 */

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			Stack stackName) {
		switch (stackName) {
		case MAX:
			criteria.setProjection(Projections.max(propertyName));
			break;
		case MIN:
			criteria.setProjection(Projections.min(propertyName));
			break;
		case AVG:
			criteria.setProjection(Projections.avg(propertyName));
			break;
		default:
			criteria.setProjection(Projections.sum(propertyName));
			break;
		}
		return this.findByCriteria(criteria, 0, 1).get(0);
	}

	@SuppressWarnings("unchecked")
	public T load(ID id) {
		return (T) getSession().load(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public T load(ID id, LockOptions lock) {
		T entity = (T) getSession().load(this.entityClass, id, lock);
		if (entity != null) {
			this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> loadAll() {

		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria.list();

	}

	public void lock(T entity, LockOptions lock) {
		getSession().buildLockRequest(lock).lock(entity);
	}

	public T save(T entity) {

		getSession().save(entity);
		return entity;
	}

	public void saveOrUpdate(Collection<T> entitys) {

		for (T entity : entitys) {
			getSession().saveOrUpdate(entity);
		}

	}

	public T saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void update(T entity, LockOptions lock) {

		getSession().buildLockRequest(lock).lock(entity);
		getSession().update(entity);
		// 如果实体不为null,立即刷新,否则锁不会生效
		this.flush();
	}

}
