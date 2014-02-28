/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.dto.GoodsCategory;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.GoodsCategoryModel;
import tk.c4se.halt.ih31.nimunimu.repository.GoodsCategoryRepository;

/**
 * @author ne_Sachirou
 * 
 */
@WebServlet("/sales/goods_category")
public class SalesGoodsCategoryController extends Controller {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		val idStr = req.getParameter("id");
		@val
		GoodsCategory goodsCategory;
		try {
			goodsCategory = new GoodsCategoryRepository().find(idStr);
		} catch (DBAccessException e) {
			e.printStackTrace();
			goodsCategory = null;
		}
		req.setAttribute("goodsCategory", goodsCategory);
		forward(req, resp, "商品category詳細", "/sales/goods_category.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		val requestType = req.getParameter("requestType");
		val model = new GoodsCategoryModel();
		try {
			if (requestType.equals("POST")) {
				model.postRequest(req, resp);
			} else if (requestType.equals("PUT")) {
				model.putRequest(req, resp);
			} else if (requestType.equals("DELETE")) {
				model.deleteRequest(req, resp);
			} else {
				resp.sendError(502, "Unknown request type: " + requestType);
				return;
			}
		} catch (DBAccessException e) {
			e.printStackTrace();
			resp.sendError(502, e.getMessage());
			return;
		}
		forward(req, resp, "商品category詳細", "/sales/goods_category.jsp");
	}
}
