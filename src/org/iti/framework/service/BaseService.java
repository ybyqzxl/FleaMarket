package org.iti.framework.service;

import java.io.Serializable;

import org.iti.entity.db.rdbms.interfaces.IRdbmsEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 所有Service接口的父接口
 *
 */
public interface BaseService extends Serializable {
	/**
	 * 推荐使用 取得jdbcTemplate进行jdbc查询
	 * 
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate();

	/**
	 * 尽量少用或者不用 取得hibernateTemplate进行HQL语句查询
	 * 
	 * @return
	 */
	public HibernateTemplate getHibernateTemplate();

	/**
	 * 保存单个对象
	 */
	public void saveIRdbmsEntity(IRdbmsEntity entity);

	/**
	 * 更新单个对象
	 * 
	 * @param entity
	 */
	public IRdbmsEntity updateIEntiy(IRdbmsEntity entity);

	/**
	 * 删除单个对象
	 * 
	 * @param entity
	 */
	public void deleteIRdbmsEntity(IRdbmsEntity entity);

	/**
	 * 根据id获取单个实体对象,仅从数据中查询
	 * 
	 * @param id
	 * @return
	 */
	public <T extends IRdbmsEntity> T loadIRdbmsEntityById(Class<T> clazz, Long id);
}
