/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.dto.Supplier;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.SupplierRepository;

/**
 * @author ne_Sachirou
 * 
 */
public class SupplierModel implements DoPostModel {
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void postRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val repo = new SupplierRepository();
		Supplier supplier = new Supplier();
		setProperties(supplier, req);
		try {
			repo.insert(supplier);
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
		val repo = new SupplierRepository();
		Supplier supplier = null;
		try {
			supplier = repo.find(idStr);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (supplier == null) {
			throw new DBAccessException("Supplier " + idStr
					+ " is not found in DB.");
		}
		setProperties(supplier, req);
		try {
			repo.update(supplier);
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
		val repo = new SupplierRepository();
		Supplier supplier = null;
		try {
			supplier = repo.find(idStr);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (supplier == null) {
			throw new DBAccessException("Supplier " + idStr
					+ " is not found in DB.");
		}
		try {
			repo.delete(supplier);
		} catch (DBAccessException e1) {
			e1.printStackTrace();
			return;
		}
	}

	private void setProperties(Supplier supplier, HttpServletRequest req) {
		supplier.setName(req.getParameter("name"));
		supplier.setZipcode(req.getParameter("zipcode"));
		supplier.setAddress(req.getParameter("address"));
		supplier.setTel(req.getParameter("tel"));
		supplier.setFax(req.getParameter("fax"));
		supplier.setPerson(req.getParameter("person"));
		supplier.setBillingCutoffDate(req.getParameter("billing_cutoff_date"));
	}
}
