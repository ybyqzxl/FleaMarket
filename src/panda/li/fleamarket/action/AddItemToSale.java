package panda.li.fleamarket.action;

import javax.annotation.Resource;

import org.iti.common.util.EncodeUtil;
import org.iti.http.interfaces.enums.HttpResponseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;
import panda.li.fleamarket.entity.SaleItem;
import panda.li.fleamarket.service.FleaMarketService;

@Controller("AddItemToSale")
@Scope("prototype")
public class AddItemToSale extends FleaMarketHttpInterfaceAction {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -6810709698897000867L;

	private String userCode;
	private String itemName;
	private String picUrl;
	private String itemDetail;
	private String price;
	private String itemType;
	private String prePrice;
	private String address;
	private String tel;

	@Resource(name = "fleaMarketService")
	private FleaMarketService fleaMarketService;

	@Override
	public String defaultExecute() throws Throwable {
		if (isEmpity(userCode) || isEmpity(itemName) || isEmpity(itemDetail) || isEmpity(price) || isEmpity(prePrice)
				|| isEmpity(itemType) || isEmpity(address) || isEmpity(tel)) {
			responState = HttpResponseState.REQ_PARAMS_ERR.name();
		} else {
			SaleItem saleItem = new SaleItem();
			saleItem.setUserCode(userCode);
			saleItem.setItemName(itemName);
			saleItem.setItemDetail(itemDetail);
			saleItem.setPrice(price);
			if (itemType == "(null)") {
				saleItem.setItemType("0");
			} else {
				saleItem.setItemType(itemType);
			}
			saleItem.setPrePrice(prePrice);
			saleItem.setPicUrl(picUrl);
			saleItem.setItemStatus(0);
			saleItem.setAddress(address);
			saleItem.setTel(tel);
			fleaMarketService.saveIRdbmsEntity(saleItem);
		}
		return ActionSupport.SUCCESS;
	}

	@Override
	public String getResponState() {
		return responState;
	}

	@Override
	public String getResponResult() {
		return responResult;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setItemName(String itemName) {
		// this.itemName = itemName;
		this.itemName = EncodeUtil.decodeHex2Str(itemName);
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setItemDetail(String itemDetail) {
		// this.itemDetail = itemDetail;
		this.itemDetail = EncodeUtil.decodeHex2Str(itemDetail);
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setPrePrice(String prePrice) {
		this.prePrice = prePrice;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setAddress(String address) {
		this.address = EncodeUtil.decodeHex2Str(address);
	}
}
