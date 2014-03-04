package tk.c4se.halt.ih31.nimunimu.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.dto.QuotationRequestSheet;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.QuotationRequestSheetRepository;

public class QuotationRequestSheetModel implements DoPostModel {

	@Override
	public void postRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val repo = new QuotationRequestSheetRepository();
		QuotationRequestSheet qrsheet = new QuotationRequestSheet();
		qrsheet.setId(Integer.parseInt(req.getParameter("id")));
		qrsheet.setAmount(Integer.parseInt(req.getParameter("amount")));
		qrsheet.setTax(Integer.parseInt(req.getParameter("tax")));
		try {
			repo.insert(qrsheet);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void putRequest(HttpServletRequest req, HttpServletResponse resp) throws DBAccessException {
		val id = req.getParameter("id");
		val repo = new QuotationRequestSheetRepository();
		QuotationRequestSheet qrsheet = null;
		try {
			qrsheet = repo.find(id);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (qrsheet == null) {
			throw new DBAccessException("QuotationRequestSheet " + id + " is not found in DB.");
		}
		qrsheet.setId(Integer.parseInt(req.getParameter("id")));
		qrsheet.setAmount(Integer.parseInt(req.getParameter("amount")));
		qrsheet.setTax(Integer.parseInt(req.getParameter("tax")));
		try {
			repo.update(qrsheet);
		} catch (DBAccessException e1) {
			e1.printStackTrace();
			throw e1;
		}
	}

	@Override
	public void deleteRequest(HttpServletRequest req, HttpServletResponse resp) throws DBAccessException {
		val id = req.getParameter("id");
		val repo = new QuotationRequestSheetRepository();
		QuotationRequestSheet qrsheet = null;
		try {
			qrsheet = repo.find(id);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (qrsheet == null) {
			throw new DBAccessException("QuotationRequestSheet " + id + " is not found in DB.");
		}
		try {
			repo.delete(qrsheet);
		} catch (DBAccessException e1) {
			e1.printStackTrace();
			return;
		}
	}
}