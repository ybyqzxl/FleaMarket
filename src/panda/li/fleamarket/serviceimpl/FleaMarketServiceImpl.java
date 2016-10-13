package panda.li.fleamarket.serviceimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iti.framework.service.BaseServiceImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import panda.li.fleamarket.entity.Requirement;
import panda.li.fleamarket.entity.SaleItem;
import panda.li.fleamarket.json.ReplyJson;
import panda.li.fleamarket.json.ReplyJson.Reply;
import panda.li.fleamarket.json.RequirementJson;
import panda.li.fleamarket.json.SaleItemJson;
import panda.li.fleamarket.json.SaleJson;
import panda.li.fleamarket.json.SaleJson.Sale;
import panda.li.fleamarket.service.FleaMarketService;

@Service("fleaMarketService")
public class FleaMarketServiceImpl extends BaseServiceImpl implements FleaMarketService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public SaleItemJson loadItemList(String itemType, long time, int upDown) {
		String sqlStr;
		if (time < 0) {
			time = System.currentTimeMillis();
		}
		if (upDown == 1) {
			sqlStr = String.format(
					"select * from SaleItem where itemType='%s' and timestamp<%s and state=0 and ItemStatus=0 limitorder by timestamp desc limit 10",
					itemType, time);
		} else {
			sqlStr = String.format(
					"select * from SaleItem where itemType='%s' and timestamp>%s and state=0 and ItemStatus=0 order by timestamp desc limit 10",
					itemType, time);
		}
		final SaleItemJson saleItemJson = new SaleItemJson();
		List<SaleItem> list = this.getJdbcTemplate().query(sqlStr, new RowMapper<SaleItem>() {

			@Override
			public SaleItem mapRow(ResultSet arg0, int arg1) throws SQLException {
				return (SaleItem) loadIRdbmsEntityById(SaleItem.class, arg0.getLong("id"));
			}
		});
		if (list != null && list.size() > 0) {
			saleItemJson.setSaleItemsList(list);
		} else {
			saleItemJson.setSaleItemsList(new ArrayList<SaleItem>());
		}
		return saleItemJson;
	}

	// 根据商品种类加载商品
	@Override
	public SaleItemJson loadItemList(String itemType, long time) {
		String sqlStr;
		if (time < 0) {
			time = System.currentTimeMillis();
		}
		sqlStr = String.format(
				"select * from SaleItem where itemType='%s' and timestamp<%s and state=0 and ItemStatus=0 order by timestamp desc limit 10",
				itemType, time);
		final SaleItemJson saleItemJson = new SaleItemJson();
		List<SaleItem> list = this.getJdbcTemplate().query(sqlStr, new RowMapper<SaleItem>() {

			@Override
			public SaleItem mapRow(ResultSet arg0, int arg1) throws SQLException {
				return (SaleItem) loadIRdbmsEntityById(SaleItem.class, arg0.getLong("id"));
			}
		});
		if (list != null && list.size() > 0) {
			saleItemJson.setSaleItemsList(list);
		} else {
			saleItemJson.setSaleItemsList(new ArrayList<SaleItem>());
		}
		return saleItemJson;
	}

	// 根据用户号和id加载商品信息
	@Override
	public SaleItem loadItemByUserCodeAndItemId(String userCode, Long id) {
		String sqlStr = String.format("select * from SaleItem where userCode='%s' and id='%s' and state=0", userCode,
				id);
		List<SaleItem> list = this.getJdbcTemplate().query(sqlStr, new RowMapper<SaleItem>() {

			@Override
			public SaleItem mapRow(ResultSet arg0, int arg1) throws SQLException {
				return (SaleItem) loadIRdbmsEntityById(SaleItem.class, arg0.getLong("id"));
			}

		});
		SaleItem saleItem = null;
		if (list != null && list.size() > 0) {
			saleItem = list.get(0);
		}
		return saleItem;
	}

	// 根据用户名读取商品信息
	@Override
	public SaleItem loadItemByUserCodeAndItemId(String userCode) {
		String sqlStr = String.format("select * from SaleItem where userCode='%s' and ItemStatus=0 and state=0",
				userCode);
		List<SaleItem> list = this.getJdbcTemplate().query(sqlStr, new RowMapper<SaleItem>() {

			@Override
			public SaleItem mapRow(ResultSet arg0, int arg1) throws SQLException {
				return (SaleItem) loadIRdbmsEntityById(SaleItem.class, arg0.getLong("id"));
			}

		});
		SaleItem saleItem = null;
		if (list != null && list.size() > 0) {
			saleItem = list.get(0);
		}
		return saleItem;
	}

	// 根据商品名称搜索商品
	@Override
	public SaleItemJson searchItemByItemName(String itemName) {

		StringBuilder sql = new StringBuilder("select * from SaleItem where ItemStatus=0 and state=0");
		if (itemName != null) {
			sql.append(" and ItemName like'%").append(itemName).append("%'");
		}
		final SaleItemJson saleItemJson = new SaleItemJson();
		List<SaleItem> list = this.getJdbcTemplate().query(sql.toString(), new RowMapper<SaleItem>() {

			@Override
			public SaleItem mapRow(ResultSet arg0, int arg1) throws SQLException {
				return (SaleItem) loadIRdbmsEntityById(SaleItem.class, arg0.getLong("id"));
			}
		});
		if (list != null && list.size() > 0) {
			saleItemJson.setSaleItemsList(list);
		} else {
			saleItemJson.setSaleItemsList(new ArrayList<SaleItem>());
		}
		return saleItemJson;
	}

	// 读取需求产品
	@Override
	public RequirementJson loadRequirement(long time) {
		if (time < 0) {
			time = System.currentTimeMillis();
		}
		String sqlStr = String.format(
				"select * from Requirement where timestamp<%s and state=0 and ItemState=0 order by timestamp desc limit 10",
				time);
		List<Requirement> list = this.getJdbcTemplate().query(sqlStr, new RowMapper<Requirement>() {

			@Override
			public Requirement mapRow(ResultSet arg0, int arg1) throws SQLException {
				return (Requirement) loadIRdbmsEntityById(Requirement.class, arg0.getLong("id"));
			}

		});
		final RequirementJson requirementJson = new RequirementJson();
		if (list != null && list.size() > 0) {
			requirementJson.setRequirementList(list);
		} else {
			requirementJson.setRequirementList(new ArrayList<Requirement>());
		}
		return requirementJson;
	}

	// 根据商品状态读取商品的信息
	@Override
	public SaleItemJson loadItemByItemStateAndUserCode(String userCode, String itemState, long time) {
		if (time < 0) {
			time = System.currentTimeMillis();
		}
		String sqlStr = String.format(
				"select * from SaleItem where userCode='%s' and timestamp<%s and state=0 and ItemStatus='%s' order by timestamp desc limit 10",
				userCode, time, itemState);
		final SaleItemJson saleItemJson = new SaleItemJson();
		List<SaleItem> list = this.getJdbcTemplate().query(sqlStr, new RowMapper<SaleItem>() {

			@Override
			public SaleItem mapRow(ResultSet arg0, int arg1) throws SQLException {
				return (SaleItem) loadIRdbmsEntityById(SaleItem.class, arg0.getLong("id"));
			}
		});
		if (list != null && list.size() > 0) {
			saleItemJson.setSaleItemsList(list);
		} else {
			saleItemJson.setSaleItemsList(new ArrayList<SaleItem>());
		}
		return saleItemJson;
	}

	// 根据用户账号读取自己的需求产品
	@Override
	public RequirementJson loadRequirementByUserCode(String userCode, long time) {
		if (time < 0) {
			time = System.currentTimeMillis();
		}
		String sqlStr = String.format(
				"select * from Requirement where userCode='%s' and timestamp<%s and state=0 order by timestamp desc limit 10",
				userCode, time);
		List<Requirement> list = this.getJdbcTemplate().query(sqlStr, new RowMapper<Requirement>() {

			@Override
			public Requirement mapRow(ResultSet arg0, int arg1) throws SQLException {
				return (Requirement) loadIRdbmsEntityById(Requirement.class, arg0.getLong("id"));
			}

		});
		final RequirementJson requirementJson = new RequirementJson();
		if (list != null && list.size() > 0) {
			requirementJson.setRequirementList(list);
		} else {
			requirementJson.setRequirementList(new ArrayList<Requirement>());
		}
		return requirementJson;
	}

	// 搜索页加载所有的商品信息
	@Override
	public SaleItemJson searchAllSaleItemList() {
		String sql = String.format("select * from SaleItem where ItemStatus=0 and state=0 order by timeStamp desc");
		List<SaleItem> list = this.getJdbcTemplate().query(sql, new RowMapper<SaleItem>() {

			@Override
			public SaleItem mapRow(ResultSet arg0, int arg1) throws SQLException {
				return (SaleItem) loadIRdbmsEntityById(SaleItem.class, arg0.getLong("id"));
			}
		});

		SaleItemJson saleItemJson = new SaleItemJson();
		if (list != null && list.size() > 0) {
			saleItemJson.setSaleItemsList(list);
		} else {
			saleItemJson.setSaleItemsList(new ArrayList<SaleItem>());
		}
		return saleItemJson;
	}

	// 加装所有留言
	@Override
	public ReplyJson loadAllReply(long replyId) {
		/**
		 * 如果该需求id对应的留言已售出，则返回null
		 */

		Requirement requirement = (Requirement) this.loadIRdbmsEntityById(Requirement.class, replyId);
		if (requirement == null) {
			return null;
		}
		/**
		 * 根据需求id得到所有留言
		 */
		String sql = String.format(
				"select * from RequirementReply where replyId='%s' and state=0 order by timeStamp asc", replyId);
		ReplyJson replyJson = new ReplyJson();
		List<Reply> list = this.getJdbcTemplate().query(sql, new RowMapper<Reply>() {

			@Override
			public Reply mapRow(ResultSet arg0, int arg1) throws SQLException {
				// return (RequirementReply)
				// loadEntityFromRDBMSById(RequirementReply.class,
				// arg0.getLong("replyId"));
				Reply reply = new Reply();
				reply.setReplyCode(arg0.getString("replyCode"));
				reply.setReplyContent(arg0.getString("replyContent"));
				reply.setTimeStamp(arg0.getLong("timeStamp"));
				return reply;
			}
		});

		if (list != null && list.size() > 0) {
			replyJson.setReply(list);
		} else {
			replyJson.setReply(new ArrayList<Reply>());
		}
		return replyJson;
	}

	@Override
	public Requirement loadRequirementByUserCodeAndItemId(String userCode, Long id) {
		String sqlStr = String.format("select * from Requirement where userCode='%s' and id='%s' and state=0", userCode,
				id);
		List<Requirement> list = this.getJdbcTemplate().query(sqlStr, new RowMapper<Requirement>() {

			@Override
			public Requirement mapRow(ResultSet arg0, int arg1) throws SQLException {
				return (Requirement) loadIRdbmsEntityById(Requirement.class, arg0.getLong("id"));
			}

		});
		Requirement requirement = null;
		if (list != null && list.size() > 0) {
			requirement = list.get(0);
		}
		return requirement;
	}

	////////////////////////////////////////////////
	// 加载最新商品
	@Override
	public SaleJson loadTheNewItemList(long time) {
		String sqlStr;
		if (time < 0) {
			time = System.currentTimeMillis();
		}
		sqlStr = String.format(
				"select top 20 * from SaleItem where timestamp<%s and state=0 and ItemStatus=0 order by timestamp desc",
				time);
		final SaleJson saleJson = new SaleJson();
		List<Sale> list = this.getJdbcTemplate().query(sqlStr, new RowMapper<Sale>() {

			@Override
			public Sale mapRow(ResultSet arg0, int arg1) throws SQLException {
				Sale sale = new Sale();
				sale.setId(arg0.getLong("id"));
				sale.setItemDetail(arg0.getString("itemDetail"));
				sale.setItemName(arg0.getString("itemName"));
				sale.setItemState(arg0.getInt("itemStatus"));
				sale.setPicUrl(arg0.getString("picUrl"));
				sale.setPrePrice(arg0.getString("prePrice"));
				sale.setPrice(arg0.getString("price"));
				sale.setTimeStamp(arg0.getLong("timeStamp"));
				sale.setUserCode(arg0.getString("userCode"));
				sale.setAddress(arg0.getString("address"));
				sale.setTel(arg0.getString("tel"));
				return sale;
			}
		});
		if (list != null && list.size() > 0) {
			saleJson.setSale(list);
		} else {
			saleJson.setSale(new ArrayList<Sale>());
		}
		return saleJson;
	}

}
