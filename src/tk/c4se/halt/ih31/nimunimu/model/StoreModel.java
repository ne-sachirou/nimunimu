/**
 *
 */
package tk.c4se.halt.ih31.nimunimu.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.dto.Store;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.StoreRepository;

/**
 * @author ne_Sachirou
 * 
 */
public class StoreModel {
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void StoreIn(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val place = req.getParameter("place");
		val goodsId = req.getParameter("goods_id");
		val repo = new StoreRepository();
		Store store = repo.find(place, goodsId);
		if (store == null) {
			store = new Store();
			store.setPlace(place);
			store.setGoodsId(goodsId);
			store.setGoodsNumber(0);
			repo.insert(store);
		}
		int goodsNumber = store.getGoodsNumber();
		goodsNumber += Integer.parseInt(req.getParameter("goods_number"));
		store.setGoodsNumber(goodsNumber);
		repo.update(store);
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void StoreOut(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val place = req.getParameter("place");
		val goodsId = req.getParameter("goods_id");
		val repo = new StoreRepository();
		Store store = repo.find(place, goodsId);
		if (store == null) {
			throw new DBAccessException("There is no Store of " + goodsId
					+ " at " + place);
		}
		int goodsNumber = store.getGoodsNumber();
		goodsNumber -= Integer.parseInt(req.getParameter("goods_number"));
		store.setGoodsNumber(goodsNumber);
		repo.update(store);
	}
}
