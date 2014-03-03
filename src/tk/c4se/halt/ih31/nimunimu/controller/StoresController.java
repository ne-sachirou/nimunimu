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

import tk.c4se.halt.ih31.nimunimu.dto.MemberAuthority;
import tk.c4se.halt.ih31.nimunimu.dto.Store;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.StoreRepository;

/**
 * @author ne_Sachirou
 * 
 */
@WebServlet("/stores")
public class StoresController extends Controller {
	private static final long serialVersionUID = 1L;

	public StoresController() {
		super();
		title = "在庫一覧";
		partial = "/stores.jsp";
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
		List<Store> stores = null;
		try {
			stores = new StoreRepository().all();
		} catch (DBAccessException e) {
			e.printStackTrace();
		}
		req.setAttribute("stores", stores);
		forward(req, resp);
	}
}
