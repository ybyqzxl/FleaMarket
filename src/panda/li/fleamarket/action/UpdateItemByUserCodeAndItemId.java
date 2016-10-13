package panda.li.fleamarket.action;

import javax.annotation.Resource;

import org.iti.http.interfaces.enums.HttpResponseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;
import panda.li.fleamarket.entity.SaleItem;
import panda.li.fleamarket.service.FleaMarketService;

@Controller("UpdateItemByUserCodeAndItemId")
@Scope("prototype")
public class UpdateItemByUserCodeAndItemId extends FleaMarketHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userCode;
	private String itemName;
	private String picUrl;
	private String itemDetail;
	private String price;
	private String prePrice;
	private long id;
	@Resource(name = "fleaMarketService")
	private FleaMarketService fleaMarketService;

	@Override
	public String defaultExecute() throws Throwable {
		if (isEmpity(userCode) || isEmpity(Long.toString(id))) {
			responState = HttpResponseState.REQ_PARAMS_ERR.name();
		} else {
			SaleItem saleItem = fleaMarketService.loadItemByUserCodeAndItemId(userCode, id);
			saleItem.setItemName(itemName);
			saleItem.setItemDetail(itemDetail);
			saleItem.setPrice(price);
			saleItem.setPicUrl(picUrl);
			saleItem.setPrePrice(prePrice);
			fleaMarketService.updateIEntiy(saleItem);
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

	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPrePrice(String prePrice) {
		this.prePrice = prePrice;
	}
}
