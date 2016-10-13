package panda.li.fleamarket.action;

import javax.annotation.Resource;

import org.iti.http.interfaces.enums.HttpResponseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;
import panda.li.fleamarket.entity.Requirement;
import panda.li.fleamarket.service.FleaMarketService;

@Controller("DeleteMyRequirement")
@Scope("prototype")
public class DeleteMyRequirement extends FleaMarketHttpInterfaceAction {

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
			Requirement requirement = fleaMarketService.loadRequirementByUserCodeAndItemId(userCode, id);
			fleaMarketService.deleteIRdbmsEntity(requirement);
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
