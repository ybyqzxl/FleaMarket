package panda.li.fleamarket.action;

import javax.annotation.Resource;

import org.iti.common.util.JsonUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;
import panda.li.fleamarket.json.RequirementJson;
import panda.li.fleamarket.service.FleaMarketService;

@Controller("LoadRequirement")
@Scope("prototype")
public class LoadRequirement extends FleaMarketHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long time;
	@Resource(name = "fleaMarketService")
	private FleaMarketService fleaMarketService;

	@Override
	public String defaultExecute() throws Throwable {
		System.out.println(System.currentTimeMillis());
		RequirementJson json = fleaMarketService.loadRequirement(time);
		responResult = JsonUtil.toJson(json);
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
