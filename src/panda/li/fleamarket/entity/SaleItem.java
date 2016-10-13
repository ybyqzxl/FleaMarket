package panda.li.fleamarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.iti.common.util.GlobeKeyBuilder;
import org.iti.entity.db.rdbms.interfaces.IRdbmsEntity;

@Entity
@Table(name = "SaleItem")
public class SaleItem implements IRdbmsEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5956293437140477431L;
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * ʱ���
	 */
	@Column
	private Long timeStamp;
	/**
	 * ʵ��״̬ 0������2ɾ��
	 */
	@Column
	private Integer state;
	/**
	 * ��ʷid
	 */
	@Column
	private Long historyId;

	@Column
	private String userCode;
	@Column
	private String itemName;
	@Column
	private String picUrl;
	@Column
	private String itemDetail;
	@Column
	private String price;
	@Column
	private String prePrice;

	// ��Ʒ״̬��0�������ۣ�1��������
	@Column
	private Integer itemStatus;
	// ��Ʒ���
	// 0����δ����ΪĬ�ϣ�1�������г���2�̲ģ�3������Ʒ��4Ь�������5�˶����ģ�6���������7�ֻ�
	@Column
	private String itemType;
	@Column
	private String address;
	@Column
	private String tel;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(Integer itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPrePrice() {
		return prePrice;
	}

	public void setPrePrice(String prePrice) {
		this.prePrice = prePrice;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
