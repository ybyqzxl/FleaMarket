package panda.li.fleamarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.iti.common.util.GlobeKeyBuilder;
import org.iti.entity.db.rdbms.interfaces.IRdbmsEntity;

@Entity
@Table(name = "Requirement")
public class Requirement implements IRdbmsEntity {

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
	private String userCode;
	@Column
	private String itemName;
	@Column
	private String itemDetail;
	@Column
	private String itemType;
	@Column
	private int price;
	// 0 表示未获得需求，1表示已得到需求不需要读取
	@Column
	private int itemState;

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDetail() {
		return itemDetail;
	}

	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setItemState(int itemState) {
		this.itemState = itemState;
	}

	public int getItemState() {
		return itemState;
	}
}
