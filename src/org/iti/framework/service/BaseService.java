package org.iti.framework.service;

import java.io.Serializable;

import org.iti.entity.db.rdbms.interfaces.IRdbmsEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * ����Service�ӿڵĸ��ӿ�
 *
 */
public interface BaseService extends Serializable {
	/**
	 * �Ƽ�ʹ�� ȡ��jdbcTemplate����jdbc��ѯ
	 * 
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate();

	/**
	 * �������û��߲��� ȡ��hibernateTemplate����HQL����ѯ
	 * 
	 * @return
	 */
	public HibernateTemplate getHibernateTemplate();

	/**
	 * ���浥������
	 */
	public void saveIRdbmsEntity(IRdbmsEntity entity);

	/**
	 * ���µ�������
	 * 
	 * @param entity
	 */
	public IRdbmsEntity updateIEntiy(IRdbmsEntity entity);

	/**
	 * ɾ����������
	 * 
	 * @param entity
	 */
	public void deleteIRdbmsEntity(IRdbmsEntity entity);

	/**
	 * ����id��ȡ����ʵ�����,���������в�ѯ
	 * 
	 * @param id
	 * @return
	 */
	public <T extends IRdbmsEntity> T loadIRdbmsEntityById(Class<T> clazz, Long id);
}
