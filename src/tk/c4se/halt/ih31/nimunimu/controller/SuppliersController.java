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
import tk.c4se.halt.ih31.nimunimu.dto.Supplier;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.SupplierRepository;

/**
 * @author ne_Sachirou
 * 
 */
@WebServlet("/suppliers")
public class SuppliersController extends Controller {
	private static final long serialVersionUID = 1L;

	public SuppliersController() {
		super();
		title = "仕入先一覧";
		partial = "/suppliers.jsp";
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
		List<Supplier> suppliers = null;
		try {
			suppliers = new SupplierRepository().all();
		} catch (DBAccessException e) {
			e.printStackTrace();
		}
		req.setAttribute("suppliers", suppliers);
		forward(req, resp);
	}
}
