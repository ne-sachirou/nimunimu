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
import tk.c4se.halt.ih31.nimunimu.dto.MemberAuthority;
import tk.c4se.halt.ih31.nimunimu.dto.QuotationRequestSheet;
import tk.c4se.halt.ih31.nimunimu.dto.QuotationRequestSheetDetail;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.QuotationRequestSheetModel;
import tk.c4se.halt.ih31.nimunimu.repository.QuotationRequestSheetDetailRepository;
import tk.c4se.halt.ih31.nimunimu.repository.QuotationRequestSheetRepository;

/**
 * @author ne_Sachirou
 *
 */
@WebServlet("/sales/quotation_request_sheet")
public class SalesQuotationRequestSheetController extends Controller {
	private static final long serialVersionUID = 1L;

	public SalesQuotationRequestSheetController() {
		super();
		title = "見積依頼書";
		partial = "/sales/quotation_request_sheet.jsp";
		authorities.add(MemberAuthority.SALES);
		authorities.add(MemberAuthority.SALES_MANAGER);
		authorities.add(MemberAuthority.ACCOUNTING);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
		val idStr = req.getParameter("id");
		@val
		QuotationRequestSheet sheet;
		try {
			sheet = new QuotationRequestSheetRepository().find(idStr);
			List<QuotationRequestSheetDetail> details =
					new QuotationRequestSheetDetailRepository().allByQuotationRequestSheetId(idStr);
			sheet.setQuotationRequestSheetDetails(details);
		} catch (DBAccessException e) {
			e.printStackTrace();
			sheet = null;
		}
		req.setAttribute("sheet", sheet);
		forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		val model = new QuotationRequestSheetModel();
		try {
			processDoPostRequest(req, resp, model);
		} catch (IOException e) {
			resp.sendError(502, e.getMessage());
			return;
		}
		forward(req, resp);
	}
}
