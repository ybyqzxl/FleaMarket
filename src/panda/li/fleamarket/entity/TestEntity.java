package panda.li.fleamarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.iti.common.util.GlobeKeyBuilder;
import org.iti.entity.db.rdbms.interfaces.IRdbmsEntity;

@Entity
@Table(name = "TestEntity")
public class TestEntity implements IRdbmsEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 时间戳
	 */
	@Column
	private Long timeStamp;
	/**
	 * 实体状态 0正常，1更新完之后的旧数据，2删除
	 */
	@Column
	private Integer state;
	/**
	 * 历史id
	 */
	@Column
	private Long historyId;

	@Column
	private String userName;
	@Column
	private String info;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String getGlobeId() {
		return GlobeKeyBuilder.keyBuilder(getClass(), id);
	}

	@Override
	public Long getTimeStamp() {
		return timeStamp;
	}

	@Override
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getHistoryId() {
		return historyId;
	}

	@Override
	public void setHistoryId(Long historyId) {
		this.historyId = historyId;

	}

	@Override
	public Integer getState() {
		return state;
	}

	@Override
	public void setState(Integer state) {
		this.state = state;
	}

}
