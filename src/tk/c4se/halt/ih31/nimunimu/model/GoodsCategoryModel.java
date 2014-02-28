/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.dto.GoodsCategory;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.GoodsCategoryRepository;

/**
 * @author ne_Sachirou
 * 
 */
public class GoodsCategoryModel {
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void postRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val repo = new GoodsCategoryRepository();
		GoodsCategory goodsCategory = new GoodsCategory();
		goodsCategory.setName(req.getParameter("name"));
		try {
			repo.insert(goodsCategory);
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
		val repo = new GoodsCategoryRepository();
		GoodsCategory goodsCategory = null;
		try {
			goodsCategory = repo.find(idStr);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (goodsCategory == null) {
			throw new DBAccessException("GoodsCategory " + idStr
					+ " is not found in DB.");
		}
		goodsCategory.setName(req.getParameter("name"));
		try {
			repo.update(goodsCategory);
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
		val repo = new GoodsCategoryRepository();
		GoodsCategory goodsCategory = null;
		try {
			goodsCategory = repo.find(idStr);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (goodsCategory == null) {
			throw new DBAccessException("GoodsCategory " + idStr
					+ " is not found in DB.");
		}
		try {
			repo.delete(goodsCategory);
		} catch (DBAccessException e1) {
			e1.printStackTrace();
			return;
		}
	}
}
