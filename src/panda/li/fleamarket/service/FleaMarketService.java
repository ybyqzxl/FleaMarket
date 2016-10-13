package panda.li.fleamarket.service;

import org.iti.framework.service.BaseService;

import panda.li.fleamarket.entity.Requirement;
import panda.li.fleamarket.entity.SaleItem;
import panda.li.fleamarket.json.ReplyJson;
import panda.li.fleamarket.json.RequirementJson;
import panda.li.fleamarket.json.SaleItemJson;
import panda.li.fleamarket.json.SaleJson;

public interface FleaMarketService extends BaseService {
	/**
	 * 根据商品类别读取商品信息
	 * 
	 * @param ItemType
	 * @return
	 */
	public SaleItemJson loadItemList(String itemType, long time);

	public SaleItemJson loadItemList(String itemType, long time, int upDown);

	public SaleItem loadItemByUserCodeAndItemId(String userCode, Long id);

	public SaleItem loadItemByUserCodeAndItemId(String userCode);

	public SaleItemJson searchItemByItemName(String itemName);

	public RequirementJson loadRequirement(long time);

	public SaleItemJson loadItemByItemStateAndUserCode(String userCode, String itemState, long time);

	public RequirementJson loadRequirementByUserCode(String userCode, long time);

	public SaleItemJson searchAllSaleItemList();

	public ReplyJson loadAllReply(long replyId);

	public Requirement loadRequirementByUserCodeAndItemId(String userCode, Long id);

	public SaleJson loadTheNewItemList(long time);
}
