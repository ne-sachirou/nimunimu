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
import tk.c4se.halt.ih31.nimunimu.dto.OurOrderSheet;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.OurOrderSheetModel;
import tk.c4se.halt.ih31.nimunimu.repository.OurOrderSheetDetailRepository;
import tk.c4se.halt.ih31.nimunimu.repository.OurOrderSheetRepository;

/**
 * @author ne_Sachirou
 * 
 */
@WebServlet("store_manager/our_orders")
public class StoreManagerOurOrderSheetController extends Controller {
	private static final long serialVersionUID = 1L;

	public StoreManagerOurOrderSheetController() {
		super();
		title = "発注書詳細";
		partial = "/store_manager/our_order_sheet.jsp";
		authorities.add(MemberAuthority.ADMIN);
		authorities.add(MemberAuthority.STORE_MANAGER);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
		val idStr = req.getParameter("id");
		@val
		OurOrderSheet sheet = null;
		try {
			sheet = new OurOrderSheetRepository().find(idStr);
			if (sheet != null) {
				sheet.setOurOrderSheetDetails(new OurOrderSheetDetailRepository()
						.allByOurOrderSheetId(sheet.getId()));
			}
		} catch (DBAccessException e) {
			e.printStackTrace();
		}
		req.setAttribute("sheet", sheet);
		forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		val model = new OurOrderSheetModel();
		try {
			processDoPostRequest(req, resp, model);
		} catch (IOException e) {
			resp.sendError(502, e.getMessage());
			return;
		}
		forward(req, resp);
	}
}
