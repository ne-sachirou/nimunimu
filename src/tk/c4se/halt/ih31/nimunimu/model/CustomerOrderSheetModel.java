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
import tk.c4se.halt.ih31.nimunimu.dto.CustomerOrderSheet;
import tk.c4se.halt.ih31.nimunimu.dto.CustomerOrderSheetDetail;
import tk.c4se.halt.ih31.nimunimu.dto.CustomerOrderStatus;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.CustomerOrderRepository;
import tk.c4se.halt.ih31.nimunimu.repository.CustomerOrderSheetDetailRepository;
import tk.c4se.halt.ih31.nimunimu.repository.CustomerOrderSheetRepository;

/**
 * @author ne_Sachirou
 * 
 */
public class CustomerOrderSheetModel implements DoPostModel {
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void postRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val repo = new CustomerOrderRepository();
		val sheetRepo = new CustomerOrderSheetRepository();
		val detailRepo = new CustomerOrderSheetDetailRepository();
		CustomerOrder order = new CustomerOrder();
		setProperties(order, req);
		try {
			sheetRepo.insert(order.getCustomerOrderSheet());
			for (val detail : order.getCustomerOrderSheet()
					.getCustomerOrderSheetDetails()) {
				detailRepo.insert(detail);
			}
			order.setCustomerOrderSheetId(order.getCustomerOrderSheet().getId());
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
		val sheetRepo = new CustomerOrderSheetRepository();
		val detailRepo = new CustomerOrderSheetDetailRepository();
		CustomerOrder order = null;
		try {
			val sheet = sheetRepo.find(idStr);
			if (sheet != null) {
				order = repo.findByCustomerOrderSheetId(sheet.getId());
			}
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (order == null) {
			throw new DBAccessException("CustomerOrderSheet " + idStr
					+ " is not found in DB.");
		}
		setProperties(order, req);
		try {
			repo.update(order);
			sheetRepo.update(order.getCustomerOrderSheet());
			for (val detail : order.getCustomerOrderSheet()
					.getCustomerOrderSheetDetails()) {
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
		val sheetRepo = new CustomerOrderSheetRepository();
		CustomerOrder order = null;
		try {
			val sheet = sheetRepo.find(idStr);
			if (sheet != null) {
				order = repo.findByCustomerOrderSheetId(sheet.getId());
			}
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (order == null) {
			throw new DBAccessException("CustomerOrderSheet " + idStr
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

		CustomerOrderSheet sheet = new CustomerOrderSheet();
		int amount = 0;
		List<CustomerOrderSheetDetail> details = new ArrayList<>();
		for (int i = 1; req.getParameter("detail_goods_id" + i) != null; ++i) {
			val goodsId = req.getParameter("detail_goods_id" + i);
			val price = Integer.parseInt(req.getParameter("detail_price" + i));
			val goodsNumber = Integer.parseInt(req
					.getParameter("detail_goods_number" + i));
			CustomerOrderSheetDetail detail = new CustomerOrderSheetDetail();
			detail.setCustomerOrderSheetId(sheet.getId());
			detail.setGoodsId(goodsId);
			detail.setPrice(price);
			detail.setGoodsNumber(goodsNumber);
			amount += price * goodsNumber;
			details.add(detail);
		}
		sheet.setCustomerOrderSheetDetails(details);
		sheet.setAmount(amount);
		sheet.setTax((int) Math.floor(amount * 0.05));
		order.setCustomerOrderSheet(sheet);
	}
}
