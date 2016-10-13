package panda.li.fleamarket.action;

import javax.annotation.Resource;

import org.iti.common.util.JsonUtil;
import org.iti.http.interfaces.enums.HttpResponseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;
import panda.li.fleamarket.json.SaleItemJson;
import panda.li.fleamarket.service.FleaMarketService;

@Controller("LoadMyInsaleItem")
@Scope("prototype")
public class LoadMyInsaleItem extends FleaMarketHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userCode;
	private long time;
	private String itemState;

	@Resource(name = "fleaMarketService")
	private FleaMarketService fleaMarketService;

	@Override
	public String defaultExecute() throws Throwable {
		if (isEmpity(userCode)) {
			responState = HttpResponseState.REQ_PARAMS_ERR.name();
		} else {
			SaleItemJson saleItemJson = fleaMarketService.loadItemByItemStateAndUserCode(userCode, itemState, time);
			responResult = JsonUtil.toJson(saleItemJson);
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

	public void setTime(long time) {
		this.time = time;
	}

	public void setItemState(String itemState) {
		this.itemState = itemState;
	}
}
