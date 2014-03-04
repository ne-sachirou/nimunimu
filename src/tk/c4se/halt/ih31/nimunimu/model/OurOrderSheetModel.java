/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.dto.OurOrderSheet;
import tk.c4se.halt.ih31.nimunimu.dto.OurOrderSheetDetail;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.OurOrderSheetDetailRepository;
import tk.c4se.halt.ih31.nimunimu.repository.OurOrderSheetRepository;

/**
 * @author ne_Sachirou
 * 
 */
public class OurOrderSheetModel implements DoPostModel {
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void postRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val repo = new OurOrderSheetRepository();
		val detailRepo = new OurOrderSheetDetailRepository();
		OurOrderSheet sheet = new OurOrderSheet();
		setProperties(sheet, req);
		try {
			repo.insert(sheet);
			for (val detail : sheet.getOurOrderSheetDetails()) {
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
		val repo = new OurOrderSheetRepository();
		val detailRepo = new OurOrderSheetDetailRepository();
		OurOrderSheet sheet = null;
		try {
			sheet = repo.find(idStr);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (sheet == null) {
			throw new DBAccessException("OurOrderSheet " + idStr
					+ " is not found in DB.");
		}
		setProperties(sheet, req);
		try {
			repo.update(sheet);
			for (val detail : sheet.getOurOrderSheetDetails()) {
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
		val repo = new OurOrderSheetRepository();
		val detailRepo = new OurOrderSheetDetailRepository();
		OurOrderSheet sheet = null;
		try {
			sheet = repo.find(idStr);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (sheet == null) {
			throw new DBAccessException("Customer " + idStr
					+ " is not found in DB.");
		}
		sheet.setOurOrderSheetDetails(detailRepo.allByOurOrderSheetId(sheet
				.getId()));
		try {
			repo.delete(sheet);
		} catch (DBAccessException e1) {
			e1.printStackTrace();
			return;
		}
	}

	private void setProperties(OurOrderSheet sheet, HttpServletRequest req) {
		sheet.setAmount(Integer.parseInt(req.getParameter("amount")));
		sheet.setTax(Integer.parseInt(req.getParameter("tax")));
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
	}
}
