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

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.dto.Goods;
import tk.c4se.halt.ih31.nimunimu.dto.MemberAuthority;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.GoodsCategoryRepository;
import tk.c4se.halt.ih31.nimunimu.repository.GoodsRepository;
import tk.c4se.halt.ih31.nimunimu.repository.SupplierRepository;

/**
 * @author kei
 * 
 */
@WebServlet("/goods_list")
public class GoodsListController extends Controller {
	private static final long serialVersionUID = 1L;

	public GoodsListController() {
		super();
		title = "商品一覧";
		partial = "/goods_list.jsp";
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
		List<Goods> goodsList = null;
		try {
			goodsList = new GoodsRepository().all();
			for (val goods : goodsList) {
				goods.setGoodsCategory(new GoodsCategoryRepository().find(goods
						.getGoodsCategoryId()));
				goods.setSupplier(new SupplierRepository().find(goods
						.getSupplierId()));
			}
		} catch (DBAccessException e) {
			e.printStackTrace();
		}
		req.setAttribute("goodsList", goodsList);
		forward(req, resp);
	}
}