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
import tk.c4se.halt.ih31.nimunimu.dto.MemberAuthority;
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

	public SalesGoodsCategoryController() {
		super();
		title = "商品カテゴリー詳細";
		partial = "/sales/goods_category.jsp";
		authorities.add(MemberAuthority.ADMIN);
		authorities.add(MemberAuthority.SALES);
		authorities.add(MemberAuthority.SALES_MANAGER);
		authorities.add(MemberAuthority.STORE);
		authorities.add(MemberAuthority.STORE_MANAGER);
		authorities.add(MemberAuthority.ACCOUNTING);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
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
		forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		val model = new GoodsCategoryModel();
		try {
			processDoPostRequest(req, resp, model);
		} catch (IOException e) {
			resp.sendError(502, e.getMessage());
			return;
		}
		forward(req, resp);
	}
}
