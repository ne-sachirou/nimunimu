/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.dto.CustomerOrder;
import tk.c4se.halt.ih31.nimunimu.dto.QuotationRequestSheet;
import tk.c4se.halt.ih31.nimunimu.dto.QuotationRequestSheetDetail;
import tk.c4se.halt.ih31.nimunimu.dto.CustomerOrderStatus;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.CustomerOrderRepository;
import tk.c4se.halt.ih31.nimunimu.repository.QuotationRequestSheetDetailRepository;
import tk.c4se.halt.ih31.nimunimu.repository.QuotationRequestSheetRepository;

/**
 * @author ne_Sachirou
 * 
 */
public class QuotationRequestSheetModel implements DoPostModel {
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void postRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val repo = new CustomerOrderRepository();
		val sheetRepo = new QuotationRequestSheetRepository();
		val detailRepo = new QuotationRequestSheetDetailRepository();
		CustomerOrder order = new CustomerOrder();
		setProperties(order, req);
		try {
			sheetRepo.insert(order.getQuotationRequestSheet());
			for (val detail : order.getQuotationRequestSheet()
					.getQuotationRequestSheetDetails()) {
				detailRepo.insert(detail);
			}
			order.setQuotationRequestSheetId(order.getQuotationRequestSheet()
					.getId());
			repo.insert(order);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void putRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val idStr = req.getParameter("id");
		val repo = new CustomerOrderRepository();
		val sheetRepo = new QuotationRequestSheetRepository();
		val detailRepo = new QuotationRequestSheetDetailRepository();
		CustomerOrder order = null;
		try {
			val sheet = sheetRepo.find(idStr);
			if (sheet != null) {
				order = repo.findByQuotationRequestSheetId(sheet.getId());
			}
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (order == null) {
			throw new DBAccessException("QuotationRequestSheet " + idStr
					+ " is not found in DB.");
		}
		setProperties(order, req);
		try {
			repo.update(order);
			sheetRepo.update(order.getQuotationRequestSheet());
			for (val detail : order.getQuotationRequestSheet()
					.getQuotationRequestSheetDetails()) {
				detailRepo.update(detail);
			}
		} catch (DBAccessException e1) {
			e1.printStackTrace();
			throw e1;
		}
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void deleteRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val idStr = req.getParameter("id");
		val repo = new CustomerOrderRepository();
		val sheetRepo = new QuotationRequestSheetRepository();
		CustomerOrder order = null;
		try {
			val sheet = sheetRepo.find(idStr);
			if (sheet != null) {
				order = repo.findByQuotationRequestSheetId(sheet.getId());
			}
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (order == null) {
			throw new DBAccessException("QuotationRequestSheet " + idStr
					+ " is not found in DB.");
		}
		try {
			repo.delete(order);
		} catch (DBAccessException e1) {
			e1.printStackTrace();
			return;
		}
	}

	private void setProperties(CustomerOrder order, HttpServletRequest req) {
		order.setCustomerId(Integer.parseInt(req
				.getParameter("order_customer_id")));
		order.setMemberId(req.getParameter("order_member_id"));
		order.setStatus(CustomerOrderStatus.valueOf(req
				.getParameter("order_status")));

		QuotationRequestSheet sheet = new QuotationRequestSheet();
		int amount = 0;
		List<QuotationRequestSheetDetail> details = new ArrayList<>();
		for (int i = 1; req.getParameter("detail_goods_id" + i) != null; ++i) {
			val goodsId = req.getParameter("detail_goods_id" + i);
			val price = Integer.parseInt(req.getParameter("detail_price" + i));
			val goodsNumber = Integer.parseInt(req
					.getParameter("detail_goods_number" + i));
			QuotationRequestSheetDetail detail = new QuotationRequestSheetDetail();
			detail.setQuotationRequestSheetId(sheet.getId());
			detail.setGoodsId(goodsId);
			detail.setPrice(price);
			detail.setGoodsNumber(goodsNumber);
			amount += price * goodsNumber;
			details.add(detail);
		}
		sheet.setQuotationRequestSheetDetails(details);
		sheet.setAmount(amount);
		sheet.setTax((int) Math.floor(amount * 0.05));
		order.setQuotationRequestSheet(sheet);
	}
}
