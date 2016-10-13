package panda.li.fleamarket.action;

import javax.annotation.Resource;

import org.iti.common.util.JsonUtil;
import org.iti.http.interfaces.enums.HttpResponseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;
import panda.li.fleamarket.json.ReplyJson;
import panda.li.fleamarket.service.FleaMarketService;

@Controller("LoadReply")
@Scope("prototype")
public class LoadReply extends FleaMarketHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long replyId;// 需求id编号
	@Resource(name = "fleaMarketService")
	private FleaMarketService fleaMarketService;

	@Override
	public String defaultExecute() throws Throwable {
		if (isEmpity(Long.toString(replyId))) {
			responState = HttpResponseState.REQ_PARAMS_ERR.name();
		} else {
			ReplyJson replyJson = fleaMarketService.loadAllReply(replyId);
			if (replyJson == null) {
				responState = HttpResponseState.REQ_PARAMS_ERR.name();
			} else {
				responResult = JsonUtil.toJson(replyJson);
			}
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

	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}
}
