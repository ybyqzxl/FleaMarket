package panda.li.fleamarket.action;

import javax.annotation.Resource;

import org.iti.common.util.EncodeUtil;
import org.iti.http.interfaces.enums.HttpResponseState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import panda.li.fleamarket.abstracthttp.FleaMarketHttpInterfaceAction;
import panda.li.fleamarket.entity.Requirement;
import panda.li.fleamarket.entity.RequirementReply;
import panda.li.fleamarket.service.FleaMarketService;

@Controller("PublishReply")
@Scope("prototype")
public class PublishReply extends FleaMarketHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long replyId;// 需求id编号
	// private String userCode;// 需求发布者的账号
	private String replyCode;// 留言回复这的账号
	private String replyContent;// 留言内容
	private String content;
	@Resource(name = "fleaMarketService")
	private FleaMarketService fleaMarketService;

	@Override
	public String defaultExecute() throws Throwable {
		if (isEmpity(Long.toString(replyId)) || isEmpity(replyCode) || isEmpity(replyContent)) {
			responState = HttpResponseState.REQ_PARAMS_ERR.name();
		} else {
			try {
				RequirementReply requirementReply = new RequirementReply();
				requirementReply.setReplyId(replyId);
				requirementReply.setReplyContent(replyContent);
				requirementReply.setReplyCode(replyCode);
				Requirement requirement = (Requirement) fleaMarketService.loadIRdbmsEntityById(Requirement.class,
						replyId);
				String authUserRequirementCreator = requirement.getUserCode();
				// 留言创建者的账号
				requirementReply.setUserCode(authUserRequirementCreator);
				fleaMarketService.saveIRdbmsEntity(requirementReply);
				content = replyCode + "给您留言:" + replyContent;
				// pushMsg(authUserRequirementCreator, content);

			} catch (Exception e) {
				responState = HttpResponseState.REQ_UNKNOW_ERR.name();
				e.printStackTrace();
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

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = EncodeUtil.decodeHex2Str(replyContent);
		// this.replyContent = replyContent;
	}

}
