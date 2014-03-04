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
import tk.c4se.halt.ih31.nimunimu.dto.OurOrder;
import tk.c4se.halt.ih31.nimunimu.dto.OurOrderSheet;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.OurOrderModel;
import tk.c4se.halt.ih31.nimunimu.repository.OurOrderRepository;
import tk.c4se.halt.ih31.nimunimu.repository.OurOrderSheetDetailRepository;
import tk.c4se.halt.ih31.nimunimu.repository.OurOrderSheetRepository;

/**
 * @author ne_Sachirou
 * 
 */
@WebServlet("store_manager/our_order")
public class StoreManagerOurOrderController extends Controller {
	private static final long serialVersionUID = 1L;

	public StoreManagerOurOrderController() {
		super();
		title = "発注詳細";
		partial = "/store_manager/our_order.jsp";
		authorities.add(MemberAuthority.ADMIN);
		authorities.add(MemberAuthority.STORE_MANAGER);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
		val idStr = req.getParameter("id");
		OurOrder order = null;
		try {
			order = new OurOrderRepository().find(idStr);
			if (order != null) {
				OurOrderSheet sheet = new OurOrderSheetRepository().find(order
						.getOurOrderSheetId());
				if (sheet != null) {
					val details = new OurOrderSheetDetailRepository()
							.allByOurOrderSheetId(sheet.getId());
					sheet.setOurOrderSheetDetails(details);
				}
				order.setOurOrderSheet(sheet);
			}
		} catch (DBAccessException e) {
			e.printStackTrace();
			resp.sendError(502, e.getMessage());
			return;
		}
		req.setAttribute("order", order);
		forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		val model = new OurOrderModel();
		try {
			processDoPostRequest(req, resp, model);
		} catch (IOException e) {
			resp.sendError(502, e.getMessage());
			return;
		}
		forward(req, resp);
	}
}
