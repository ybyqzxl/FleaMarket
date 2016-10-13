package panda.li.fleamarket.action;

import javax.annotation.Resource;

import org.iti.common.util.JsonUtil;
import org.iti.http.interfaces.enums.HttpResponseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;
import panda.li.fleamarket.json.SaleJson;
import panda.li.fleamarket.service.FleaMarketService;

@Controller("LoadTheNewSale")
@Scope("prototype")
public class LoadTheNewSale extends FleaMarketHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long time;
	@Resource(name = "fleaMarketService")
	private FleaMarketService fleaMarketService;

	@Override
	public String defaultExecute() throws Throwable {
		if (isEmpity(Long.toString(time))) {
			responState = HttpResponseState.REQ_PARAMS_ERR.name();
		} else {
			SaleJson saleJson = fleaMarketService.loadTheNewItemList(time);
			responResult = JsonUtil.toJson(saleJson);
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

	public void setTime(long time) {
		this.time = time;
	}

}
