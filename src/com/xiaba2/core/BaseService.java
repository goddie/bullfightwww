package com.xiaba2.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.xiaba2.core.IBaseDao.Stack;

@EnableTransactionManagement
public abstract class BaseService<T, ID extends Serializable> {

	Logger logger = Logger.getLogger(BaseService.class.getName());

	/** 泛型对应的Class定义 */
	protected Class<?> entityClass;

	public BaseService() {
		// 通过反射取得Entity的Class.
		try {
			Object genericClz = getClass().getGenericSuperclass();
			if (genericClz instanceof ParameterizedType) {
				entityClass = (Class<?>) ((ParameterizedType) getClass()
						.getGenericSuperclass()).getActualTypeArguments()[0];
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error detail:", e);
		}
	}

	/**
	 * 
	 * @param hql
	 *            使用hql语句进行数据库增删改操作
	 * @return 受影响行的记录数
	 */
	@Transactional
	public Integer bulkUpdate(String hql) {

		return getEntityDao().bulkUpdate(hql);
	}

	/**
	 * 
	 * @param hql
	 *            使用hql语句进行数据库增删改操作
	 * @param params
	 *            hql语句参数
	 * @return 受影响行的记录数
	 */
	@Transactional
	public Integer bulkUpdate(String hql, Object... values) {

		return getEntityDao().bulkUpdate(hql, values);
	}

	/**
	 * 
	 * @return 创建与会话绑定的检索标准对象
	 */

	public Criteria createCriteria() {

		return getEntityDao().createCriteria();
	}

	/**
	 * 
	 * @return 根据SimpleDao泛型类型,创建一个与会话无关的检索对象
	 */
	public DetachedCriteria createDetachedCriteria() {

		return getEntityDao().createDetachedCriteria();
	}

	/**
	 * 
	 * @param c
	 *            为一个实体类型
	 * @return 根据指定的类型创建一个与会话无关的检索对象
	 */
	public DetachedCriteria createDetachedCriteria(
			Class<? extends Serializable> c) {

		return getEntityDao().createDetachedCriteria(c);
	}

	/**
	 * 
	 * @param entitys
	 *            删除多个实体 @ 抛出异常
	 */
	@Transactional
	public void delete(Collection<T> entitys) {

		getEntityDao().delete(entitys);
	}

	/**
	 * 
	 * @param entity
	 *            删除一个实体 @ 抛出异常
	 */
	@Transactional
	public void delete(T entity) {

		getEntityDao().delete(entity);
	}

	/**
	 * 
	 * @param entity
	 *            删除一个实体
	 * @param lock
	 *            加锁实体 @ 抛出异常
	 */
	@Transactional
	public void delete(T entity, LockOptions lock) {

		getEntityDao().delete(entity, lock);
	}

	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @return 一个list集合
	 */
	@Transactional(readOnly = true)
	public List<T> find(String hql) {

		return getEntityDao().find(hql);
	}

	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @param params
	 *            hql语句参数
	 * @return 一个list集合
	 */
	@Transactional(readOnly = true)
	public List<T> find(String hql, Object... values) {

		return getEntityDao().find(hql, values);
	}

	/**
	 * 
	 * @param criteria
	 *            使用指定的检索标准来检索数
	 * @return 一个list集合
	 */
	@Transactional(readOnly = true)
	public List<T> findByCriteria(DetachedCriteria criteria) {

		return getEntityDao().findByCriteria(criteria);
	}

	/**
	 * 
	 * @param criteria
	 *            使用指定的检索标准来分页检索数据
	 * @param firstResult
	 *            开始条数
	 * @param maxResults
	 *            返回记录数
	 * @return 一个list集合
	 */
	@Transactional(readOnly = true)
	public List<T> findByCriteria(DetachedCriteria criteria,
			Integer firstResult, Integer maxResults) {

		return getEntityDao().findByCriteria(criteria, firstResult, maxResults);
	}

	/**
	 * 使用指定的检索标准来检索数
	 * 
	 * @param criteria
	 * @param pageIndex
	 * @param pageSize
	 * @return 分页实体
	 */
	@Transactional(readOnly = true)
	public Page<T> findPageByCriteria(DetachedCriteria criteria, Page<T> page)
	{
		return getEntityDao().findPageByCriteria(criteria, page);
	}

	/**
	 * 通过原生SQL查询
	 * 
	 * @param sql
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> findByNativeSQL(String sql) {

		return getEntityDao().findByNativeSQL(sql);
	}

	/**
	 * 强制立即更新到数据库,否则需要事务提交后,才会提交到数据库
	 */

	public void flush() {

		getEntityDao().flush();
	}

	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @return 一个实体对象
	 */
	@Transactional(readOnly = true)
	public T get(ID id) {
		return getEntityDao().get(id);
	}

	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @param lock
	 *            加锁实体
	 * @return 一个实体对象
	 */
	@Transactional(readOnly = true)
	public T get(ID id, LockOptions lock) {
		return getEntityDao().get(id, lock);

	}

	/** 子类设置具体的DAO对象实例 */
	abstract protected IBaseDao<T, ID> getEntityDao();

	/**
	 * 查询数据库对应的记录数
	 * 
	 * @param criteria
	 *            一个DetachedCriteria对象
	 * @return 记录数
	 */
	@Transactional(readOnly = true)
	public Long getRowCount(DetachedCriteria criteria) {

		return getEntityDao().getRowCount(criteria);
	}

	/**
	 * 使用数据库函数
	 * 
	 * @param criteria
	 *            一个DetacherCriteria对象
	 * @param propertyName
	 *            实体类属性名
	 * @param stackName
	 *            Stack枚举类型中的任意一个
	 * @return 一行一列数据库
	 */
	@Transactional(readOnly = true)
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			Stack stackName) {
		return getEntityDao().getStackValue(criteria, propertyName, stackName);

	}

	/**
	 * 
	 * @param id
	 *            根据主键加裁一个实体对象
	 * @return 一个实体对象
	 */
	@Transactional(readOnly = true)
	public T load(ID id) {

		return getEntityDao().load(id);
	}

	/**
	 * 
	 * @param id
	 *            根据主键加裁实体
	 * @param lock
	 *            加锁实体
	 * @return 一个实体对象
	 */
	@Transactional(readOnly = true)
	public T load(ID id, LockOptions lock) {

		return getEntityDao().load(id, lock);
	}

	/**
	 * 
	 * @return 加裁所有对象
	 */
	@Transactional(readOnly = true)
	public List<T> loadAll() {

		return getEntityDao().loadAll();
	}

	/**
	 * 加锁指定的实体
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 * @param lock
	 *            加锁
	 */
	@Transactional(readOnly = true)
	public void lock(T entity, LockOptions lock) {

		getEntityDao().lock(entity, lock);
	}

	/**
	 * 
	 * @param entity
	 *            保存一个实体 @ 抛出Exception异常
	 */
	@Transactional
	public T save(T entity) {

		return getEntityDao().save(entity);
	}

	/**
	 * 
	 * @param entitys
	 *            保存多个实体 @ 抛出异常
	 */
	@Transactional
	public void saveOrUpdate(Collection<T> entitys) {

		getEntityDao().saveOrUpdate(entitys);
	}

	/**
	 * 
	 * @param entity
	 *            当实体在数据库不在在与之对应记录时,则保存实体,在在实体,则更新实体 @ 抛出异常
	 */
	@Transactional
	public T saveOrUpdate(T entity) {

		return getEntityDao().saveOrUpdate(entity);
	}

	/**
	 * 
	 * @param entity
	 *            修改一个实体 @ 抛出异常
	 */
	@Transactional
	public void update(T entity) {

		getEntityDao().update(entity);
	}

	/**
	 * 
	 * @param entity
	 *            修改一个实体
	 * @param lock
	 *            加锁实体 @ 抛出异常
	 */
	@Transactional
	public void update(T entity, LockOptions lock) {

		getEntityDao().update(entity, lock);
	}

}
