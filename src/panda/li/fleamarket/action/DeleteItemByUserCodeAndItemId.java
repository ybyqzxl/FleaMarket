package panda.li.fleamarket.action;

import javax.annotation.Resource;

import org.iti.http.interfaces.enums.HttpResponseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;
import panda.li.fleamarket.entity.SaleItem;
import panda.li.fleamarket.service.FleaMarketService;

@Controller("DeleteItemByUserCodeAndItemId")
@Scope("prototype")
public class DeleteItemByUserCodeAndItemId extends FleaMarketHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userCode;
	private long id;
	@Resource(name = "fleaMarketService")
	private FleaMarketService fleaMarketService;

	@Override
	public String defaultExecute() throws Throwable {
		if (isEmpity(userCode)) {
			responState = HttpResponseState.REQ_PARAMS_ERR.name();
		} else {
			SaleItem saleItem = fleaMarketService.loadItemByUserCodeAndItemId(userCode, id);
			fleaMarketService.deleteIRdbmsEntity(saleItem);
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

	public void setId(long id) {
		this.id = id;
	}

}
