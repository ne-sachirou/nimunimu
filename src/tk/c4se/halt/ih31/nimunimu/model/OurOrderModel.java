/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.dto.OurOrder;
import tk.c4se.halt.ih31.nimunimu.dto.OurOrderSheet;
import tk.c4se.halt.ih31.nimunimu.dto.OurOrderSheetDetail;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.OurOrderRepository;
import tk.c4se.halt.ih31.nimunimu.repository.OurOrderSheetDetailRepository;
import tk.c4se.halt.ih31.nimunimu.repository.OurOrderSheetRepository;

/**
 * @author ne_Sachirou
 * 
 */
public class OurOrderModel implements DoPostModel {
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void postRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val repo = new OurOrderRepository();
		val sheetRepo = new OurOrderSheetRepository();
		val detailRepo = new OurOrderSheetDetailRepository();
		OurOrder order = new OurOrder();
		setProperties(order, req);
		try {
			repo.insert(order);
			sheetRepo.insert(order.getOurOrderSheet());
			for (val detail : order.getOurOrderSheet()
					.getOurOrderSheetDetails()) {
				detailRepo.insert(detail);
			}
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
		val repo = new OurOrderRepository();
		val sheetRepo = new OurOrderSheetRepository();
		val detailRepo = new OurOrderSheetDetailRepository();
		OurOrder order = null;
		try {
			order = repo.find(idStr);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (order == null) {
			throw new DBAccessException("OurOrder " + idStr
					+ " is not found in DB.");
		}
		setProperties(order, req);
		try {
			repo.update(order);
			sheetRepo.update(order.getOurOrderSheet());
			for (val detail : order.getOurOrderSheet()
					.getOurOrderSheetDetails()) {
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
		val repo = new OurOrderRepository();
		OurOrder order = null;
		try {
			order = repo.find(idStr);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (order == null) {
			throw new DBAccessException("Customer " + idStr
					+ " is not found in DB.");
		}
		try {
			repo.delete(order);
		} catch (DBAccessException e1) {
			e1.printStackTrace();
			return;
		}
	}

	private void setProperties(OurOrder order, HttpServletRequest req) {
		order.setSupplierId(Integer.parseInt(req.getParameter("supplier_id")));
		order.setMemberId(req.getParameter("member_id"));
		OurOrderSheet sheet = new OurOrderSheet();
		sheet.setAmount(Integer.parseInt(req.getParameter("sheet_amount")));
		sheet.setTax(Integer.parseInt(req.getParameter("sheet_tax")));
		List<OurOrderSheetDetail> details = new ArrayList<>();
		for (int i = 1; req.getParameter("detail_goods_id" + i) != null; ++i) {
			val goodsId = req.getParameter("detail_goods_id" + i);
			val price = Integer.parseInt(req.getParameter("detail_price" + i));
			val goodsNumber = Integer.parseInt(req
					.getParameter("detail_goods_number" + i));
			OurOrderSheetDetail detail = new OurOrderSheetDetail();
			detail.setOurOrderSheetId(sheet.getId());
			detail.setGoodsId(goodsId);
			detail.setPrice(price);
			detail.setGoodsNumber(goodsNumber);
			details.add(detail);
		}
		sheet.setOurOrderSheetDetails(details);
		order.setOurOrderSheet(sheet);
	}
}
