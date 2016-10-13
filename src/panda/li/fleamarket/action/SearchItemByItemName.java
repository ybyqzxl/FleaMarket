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

@Controller("SearchItemByItemName")
@Scope("prototype")
public class SearchItemByItemName extends FleaMarketHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String itemName;

	@Resource(name = "fleaMarketService")
	private FleaMarketService fleaMarketService;

	@Override
	public String defaultExecute() throws Throwable {
		if (isEmpity(itemName)) {
			responState = HttpResponseState.REQ_PARAMS_ERR.name();
		} else {
			SaleItemJson json = fleaMarketService.searchItemByItemName(itemName);
			responResult = JsonUtil.toJson(json);
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
		this.itemName = itemName;
	}

}
