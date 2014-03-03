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
import tk.c4se.halt.ih31.nimunimu.dto.MemberAuthority;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.StoreModel;

/**
 * @author ne_Sachirou
 * 
 */
@WebServlet("/store_out")
public class StoreOutController extends Controller {
	private static final long serialVersionUID = 1L;

	public StoreOutController() {
		super();
		title = "出庫入力";
		partial = "/store_out.jsp";
		authorities.add(MemberAuthority.SALES_MANAGER);
		authorities.add(MemberAuthority.STORE);
		authorities.add(MemberAuthority.STORE_MANAGER);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
		forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		val model = new StoreModel();
		try {
			model.StoreOut(req, resp);
		} catch (DBAccessException e) {
			e.printStackTrace();
			resp.sendError(502, e.getMessage());
			return;
		}
		forward(req, resp);
	}
}
