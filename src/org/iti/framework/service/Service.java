package org.iti.framework.service;

import java.io.Serializable;

import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.entity.db.rdbms.interfaces.IRdbmsEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class Service implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3336305945600396030L;

	/**
	 * �Ƽ�ʹ�� ȡ��jdbcTemplate����jdbc��ѯ
	 * 
	 * @return
	 */
	public static final JdbcTemplate getJdbcTemplate() {
		BaseService baseService = getBaseService();
		return baseService.getJdbcTemplate();
	}

	/**
	 * ��ȡhibernateTemplate
	 * 
	 * @return
	 */
	public static final HibernateTemplate getHibernateTemplate() {
		BaseService baseService = getBaseService();
		return baseService.getHibernateTemplate();
	}

	/**
	 * �����ݿ�����Ӽ�¼
	 * 
	 * @param entity
	 */
	public static void saveEntityToRDBMS(IRdbmsEntity entity) {
		BaseService baseService = getBaseService();
		baseService.saveIRdbmsEntity(entity);
	}

	/**
	 * �����ݿ���ɾ����¼
	 * 
	 * @param entity
	 */
	public static void deleteEntityFromRDBMS(IRdbmsEntity entity) {
		BaseService baseService = getBaseService();
		baseService.deleteIRdbmsEntity(entity);
	}

	/**
	 * ���¼�¼
	 * 
	 * @param entity
	 * @return
	 */
	public static IRdbmsEntity updateEntityToRDBMS(IRdbmsEntity entity) {
		BaseService baseService = getBaseService();
		return baseService.updateIEntiy(entity);
	}

	/**
	 * ��ȡ��¼
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unused")
	private static <T extends IRdbmsEntity> T loadEntityFromRDBMSById(
			Class<T> clazz, Long id) {
		BaseService baseService = getBaseService();
		return (T) baseService.loadIRdbmsEntityById(clazz, id);
	}

	private static BaseService getBaseService() {
		BaseService baseService = (BaseService) BeanFactory
				.getBean("baseService");
		return baseService;
	}
}
