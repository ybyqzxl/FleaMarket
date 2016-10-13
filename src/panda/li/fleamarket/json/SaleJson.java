package panda.li.fleamarket.json;

import java.util.List;

public class SaleJson {

	List<Sale> sale;

	public List<Sale> getSale() {
		return sale;
	}

	public void setSale(List<Sale> sale) {
		this.sale = sale;
	}

	public static class Sale {
		private long id;
		private String userCode;
		private Integer itemState;
		private String picUrl;
		private String price;
		private String prePrice;
		private long timeStamp;
		private String itemName;
		private String itemDetail;
		private String address;
		private String tel;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getUserCode() {
			return userCode;
		}

		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}

		public Integer getItemState() {
			return itemState;
		}

		public void setItemState(Integer itemState) {
			this.itemState = itemState;
		}

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getPrePrice() {
			return prePrice;
		}

		public void setPrePrice(String prePrice) {
			this.prePrice = prePrice;
		}

		public long getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(long timeStamp) {
			this.timeStamp = timeStamp;
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

}
