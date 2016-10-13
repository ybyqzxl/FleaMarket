/**
 * 
 */
package org.iti.framework.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.iti.entity.db.rdbms.interfaces.IRdbmsEntity;
import org.iti.entity.interfaces.IEntity.EntityState;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service("baseService")
public class BaseServiceImpl implements BaseService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1591515185221768779L;

	protected JdbcTemplate jdbcTemplate;
	@Resource(name = "hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;

	@Resource
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	@Override
	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Override
	public void saveIRdbmsEntity(IRdbmsEntity entity) {
		try {
			if (entity == null)
				throw new NullPointerException("Entity is NULL");
			entity.setTimeStamp(new Date().getTime());
			if (entity.getId() == null)
				entity.setState(EntityState.NORMAL.getValue());
			this.hibernateTemplate.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void saveEntityToRDBMSCore(IRdbmsEntity entity) {
		try {
			if (entity == null)
				throw new NullPointerException("Entity is NULL");
			this.hibernateTemplate.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public IRdbmsEntity updateIEntiy(IRdbmsEntity entity) {
		try {
			if (entity == null || entity.getId() == null)
				throw new NullPointerException("entity or entity.id is NULL");
			// 存储历史记录
			IRdbmsEntity history = loadIRdbmsEntityById(entity.getClass(), entity.getId(), null);
			history.setId(null);
			history.setState(EntityState.DISABLE.getValue());
			history.setTimeStamp(new Date().getTime());
			saveEntityToRDBMSCore(history);
			// 更新记录
			entity.setHistoryId(history.getId());
			saveIRdbmsEntity(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void deleteIRdbmsEntity(IRdbmsEntity entity) {
		try {
			if (entity == null || entity.getId() == null)
				throw new NullPointerException("entity or entity.id is NULL");
			entity.setState(EntityState.DELETED.getValue());
			saveIRdbmsEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@SuppressWarnings("unchecked")
	private <T extends IRdbmsEntity> T loadIRdbmsEntityById(Class<T> clazz, Long id, EntityState state) {

		try {
			if (id == null)
				throw new NullPointerException("ID is NULL");
			IRdbmsEntity entity = (IRdbmsEntity) this.hibernateTemplate.get(clazz, id);
			this.hibernateTemplate.evict(entity);
			if (entity == null) {
				return null;
			}
			if (state == null)
				return (T) entity;
			else {
				if (entity.getState().equals(state.getValue()))
					return (T) entity;
				else
					return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public <T extends IRdbmsEntity> T loadIRdbmsEntityById(Class<T> clazz, Long id) {
		return loadIRdbmsEntityById(clazz, id, EntityState.NORMAL);
	}
}
