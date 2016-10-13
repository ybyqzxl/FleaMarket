package panda.li.fleamarket.action;

import javax.annotation.Resource;

import org.iti.common.util.JsonUtil;
import org.iti.http.interfaces.enums.HttpResponseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;
import panda.li.fleamarket.json.RequirementJson;
import panda.li.fleamarket.service.FleaMarketService;

@Controller("LoadMyRequirement")
@Scope("prototype")
public class LoadMyRequirement extends FleaMarketHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userCode;
	private long time;
	@Resource(name = "fleaMarketService")
	private FleaMarketService fleaMarketService;

	@Override
	public String defaultExecute() throws Throwable {
		if (isEmpity(userCode)) {
			responState = HttpResponseState.REQ_PARAMS_ERR.name();
		} else {
			RequirementJson requirementJson = fleaMarketService.loadRequirementByUserCode(userCode, time);
			responResult = JsonUtil.toJson(requirementJson);
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

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
