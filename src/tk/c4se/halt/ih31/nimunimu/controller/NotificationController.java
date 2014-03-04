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
import tk.c4se.halt.ih31.nimunimu.dto.Notification;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.NotificationRepository;

/**
 * @author kei
 *
 */
@WebServlet("/notification")
public class NotificationController extends Controller {
	private static final long serialVersionUID = 1L;

	public NotificationController() {
		super();
		title = "お報せ詳細";
		partial = "/notification.jsp";
		/*
		authorities.add(MemberAuthority.ADMIN);
		authorities.add(MemberAuthority.SALES);
		authorities.add(MemberAuthority.SALES_MANAGER);
		authorities.add(MemberAuthority.STORE);
		authorities.add(MemberAuthority.STORE_MANAGER);
		authorities.add(MemberAuthority.ACCOUNTING);
		*/
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
		val id = req.getParameter("id");
		@val
		Notification notification;
		try {
			notification = new NotificationRepository().find(id);
		} catch (DBAccessException e) {
			e.printStackTrace();
			notification = null;
		}
		req.setAttribute("notification", notification);
		forward(req, resp);
	}
}