package panda.li.fleamarket.action;

import javax.annotation.Resource;

import org.iti.common.util.EncodeUtil;
import org.iti.http.interfaces.enums.HttpResponseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;
import panda.li.fleamarket.entity.Requirement;
import panda.li.fleamarket.service.FleaMarketService;

@Controller("PublishMyRequirement")
@Scope("prototype")
public class PublishMyRequirement extends FleaMarketHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userCode;
	private String itemName;
	private String itemDetail;
	private String price;
	private String itemType;

	@Resource(name = "fleaMarketService")
	private FleaMarketService fleaMarketService;

	@Override
	public String defaultExecute() throws Throwable {
		if (isEmpity(userCode) || isEmpity(itemName) || isEmpity(itemDetail) || isEmpity(price) || isEmpity(itemType)) {
			responState = HttpResponseState.REQ_PARAMS_ERR.name();
		} else {
			Requirement requirement = new Requirement();
			requirement.setUserCode(userCode);
			requirement.setItemName(itemName);
			requirement.setItemDetail(itemDetail);
			requirement.setPrice(Integer.parseInt(price));
			requirement.setItemType(itemType);
			requirement.setItemState(0);
			fleaMarketService.saveIRdbmsEntity(requirement);
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

	public void setItemName(String itemName) {
		this.itemName = EncodeUtil.decodeHex2Str(itemName);
		// this.itemName = itemName;
	}

	public void setItemDetail(String itemDetail) {
		this.itemDetail = EncodeUtil.decodeHex2Str(itemDetail);
		// this.itemDetail = itemDetail;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
