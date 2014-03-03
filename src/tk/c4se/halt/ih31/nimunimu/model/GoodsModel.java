/**
 *
 */
package tk.c4se.halt.ih31.nimunimu.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.dto.Goods;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.GoodsRepository;

/**
 * @author kei
 * 
 */
public class GoodsModel implements DoPostModel {
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void postRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val repo = new GoodsRepository();
		Goods goods = new Goods();
		goods.setId(req.getParameter("id"));
		goods.setName(req.getParameter("name"));
		goods.setGoodsCategoryId(Integer.parseInt(req
				.getParameter("goods_category_id")));
		goods.setSupplierId(Integer.parseInt(req.getParameter("supplier_id")));
		goods.setPrice(Integer.parseInt(req.getParameter("price")));
		try {
			repo.insert(goods);
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
		val id = req.getParameter("id");
		val repo = new GoodsRepository();
		Goods goods = null;
		try {
			goods = repo.find(id);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (goods == null) {
			throw new DBAccessException("Goods " + id + " is not found in DB.");
		}
		goods.setName(req.getParameter("name"));
		goods.setGoodsCategoryId(Integer.parseInt(req
				.getParameter("goods_category_id")));
		goods.setSupplierId(Integer.parseInt(req.getParameter("supplier_id")));
		goods.setPrice(Integer.parseInt(req.getParameter("price")));
		try {
			repo.update(goods);
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
		val id = req.getParameter("id");
		val repo = new GoodsRepository();
		Goods goods = null;
		try {
			goods = repo.find(id);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (goods == null) {
			throw new DBAccessException("Goods " + id + " is not found in DB.");
		}
		try {
			repo.delete(goods);
		} catch (DBAccessException e1) {
			e1.printStackTrace();
			return;
		}
	}
}