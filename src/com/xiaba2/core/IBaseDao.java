package com.xiaba2.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.criterion.DetachedCriteria;

public interface IBaseDao<T, ID extends Serializable> {

	/**
	 * 
	 * 该枚举类型用于,getStackValue方法中的Stack枚举
	 * 
	 */
	enum Stack {
		MAX, MIN, AVG, SUM;
	}

	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @return 一个实体对象
	 */
	T get(ID id);

	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @param lock
	 *            加锁实体
	 * @return 一个实体对象
	 */
	T get(ID id, LockOptions lock);

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
	Object getStackValue(DetachedCriteria criteria, String propertyName,
			Stack stackName);

	/**
	 * 查询数据库对应的记录数
	 * 
	 * @param criteria
	 *            一个DetachedCriteria对象
	 * @return 记录数
	 */
	Integer getRowCount(DetachedCriteria criteria);

	/**
	 * 
	 * @param id
	 *            根据主键加裁一个实体对象
	 * @return 一个实体对象
	 */
	T load(ID id);

	/**
	 * 
	 * @param id
	 *            根据主键加裁实体
	 * @param lock
	 *            加锁实体
	 * @return 一个实体对象
	 */
	T load(ID id, LockOptions lock);

	/**
	 * 
	 * @return 加裁所有对象
	 */
	List<T> loadAll();

	/**
	 * 
	 * @param entity
	 *            保存一个实体 @ * 抛出Exception异常
	 */
	T save(T entity);

	/**
	 * 
	 * @param entity
	 *            删除一个实体 @ * 抛出异常
	 */
	void delete(T entity);

	/**
	 * 
	 * @param entity
	 *            删除一个实体
	 * @param lock
	 *            加锁实体 @ * 抛出异常
	 */
	void delete(T entity, LockOptions lock);

	/**
	 * 
	 * @param entitys
	 *            删除多个实体 @ * 抛出异常
	 */
	void delete(Collection<T> entitys);

	/**
	 * 
	 * @param entity
	 *            修改一个实体 @ * 抛出异常
	 */
	void update(T entity);

	/**
	 * 
	 * @param entity
	 *            修改一个实体
	 * @param lock
	 *            加锁实体 @ * 抛出异常
	 */
	void update(T entity, LockOptions lock);

	/**
	 * 
	 * @param entity
	 *            当实体在数据库不在在与之对应记录时,则保存实体,在在实体,则更新实体 @ * 抛出异常
	 */
	T saveOrUpdate(T entity);

	/**
	 * 
	 * @param entitys
	 *            保存多个实体 @ * 抛出异常
	 */
	void saveOrUpdate(Collection<T> entitys);

	/**
	 * 
	 * @param hql
	 *            使用hql语句进行数据库增删改操作
	 * @return 受影响行的记录数
	 */
	Integer bulkUpdate(String hql);

	/**
	 * 
	 * @param hql
	 *            使用hql语句进行数据库增删改操作
	 * @param params
	 *            hql语句参数
	 * @return 受影响行的记录数
	 */
	Integer bulkUpdate(String hql, Object... values);

	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @return 一个list集合
	 */
	List<T> find(String hql);

	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @param params
	 *            hql语句参数
	 * @return 一个list集合
	 */
	List<T> find(String hql, Object... values);

	/**
	 * 
	 * @param criteria
	 *            使用指定的检索标准来检索数
	 * @return 一个list集合
	 */
	List<T> findByCriteria(DetachedCriteria criteria);

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
	List<T> findByCriteria(DetachedCriteria criteria, Integer firstResult,
			Integer maxResults);

	 
	/**
	 * 使用指定的检索标准来检索数
	 * @param criteria
	 * @param pageIndex
	 * @param pageSize
	 * @return 分页实体
	 */
	Page<T> findPageByCriteria(DetachedCriteria criteria, Page<T> page);

	/**
	 * 加锁指定的实体
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 * @param lock
	 *            加锁
	 */
	void lock(T entity, LockOptions lock);

	/**
	 * 强制立即更新到数据库,否则需要事务提交后,才会提交到数据库
	 */
	void flush();

	/**
	 * 
	 * @return 根据SimpleDao泛型类型,创建一个与会话无关的检索对象
	 */
	DetachedCriteria createDetachedCriteria();

	/**
	 * 
	 * @param c
	 *            为一个实体类型
	 * @return 根据指定的类型创建一个与会话无关的检索对象
	 */
	DetachedCriteria createDetachedCriteria(Class<? extends Serializable> c);

	/**
	 * 
	 * @return 创建与会话绑定的检索标准对象
	 */
	Criteria createCriteria();

	/**
	 * 通过原生SQL查询
	 * 
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> findByNativeSQL(String sql);
}
