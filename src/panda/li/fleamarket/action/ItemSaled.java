package panda.li.fleamarket.action;

import javax.annotation.Resource;

import org.iti.http.interfaces.enums.HttpResponseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;
import panda.li.fleamarket.entity.SaleItem;
import panda.li.fleamarket.service.FleaMarketService;

@Controller("ItemSaled")
@Scope("prototype")
public class ItemSaled extends FleaMarketHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String itemState;
	private long id;

	@Resource(name = "fleaMarketService")
	private FleaMarketService fleaMarketService;

	@Override
	public String defaultExecute() throws Throwable {
		if (isEmpity(Long.toString(id))) {
			responState = HttpResponseState.REQ_PARAMS_ERR.name();
		} else {
			SaleItem saleItem = (SaleItem) fleaMarketService.loadIRdbmsEntityById(SaleItem.class, id);
			saleItem.setItemStatus(1);
			fleaMarketService.updateIEntiy(saleItem);
		}
		return super.defaultExecute();
	}

	@Override
	public String getResponState() {
		return responState;
	}

	@Override
	public String getResponResult() {
		return responResult;
	}

	public void setId(long id) {
		this.id = id;
	}

}
