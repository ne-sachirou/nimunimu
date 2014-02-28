/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.c4se.halt.ih31.nimunimu.dto.GoodsCategory;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.GoodsCategoryRepository;

/**
 * @author ne_Sachirou
 * 
 */
@WebServlet("/sales/goods_categories")
public class SalesGoodsCategoriesController extends Controller {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<GoodsCategory> goodsCategories = null;
		try {
			goodsCategories = new GoodsCategoryRepository().all();
		} catch (DBAccessException e) {
			e.printStackTrace();
		}
		req.setAttribute("goodsCategories", goodsCategories);
		forward(req, resp, "商品category一覧", "/sales/goods_categories.jsp");
	}
}
