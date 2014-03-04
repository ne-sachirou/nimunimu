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
import tk.c4se.halt.ih31.nimunimu.dto.Supplier;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.SupplierModel;
import tk.c4se.halt.ih31.nimunimu.repository.SupplierRepository;

/**
 * @author ne_Sachirou
 * 
 */
@WebServlet("/supplier")
public class SupplierController extends Controller {
	private static final long serialVersionUID = 1L;

	public SupplierController() {
		super();
		title = "仕入先詳細";
		partial = "/supplier.jsp";
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
		Supplier supplier = null;
		try {
			supplier = new SupplierRepository().find(idStr);
		} catch (DBAccessException e) {
			e.printStackTrace();
			resp.sendError(502, e.getMessage());
		}
		if (supplier == null) {
			resp.sendError(404, "Supplier " + idStr + "is not found in DB.");
			return;
		}
		req.setAttribute("supplier", supplier);
		forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		val model = new SupplierModel();
		try {
			processDoPostRequest(req, resp, model);
		} catch (IOException e) {
			resp.sendError(502, e.getMessage());
			return;
		}
		forward(req, resp);
	}
}
