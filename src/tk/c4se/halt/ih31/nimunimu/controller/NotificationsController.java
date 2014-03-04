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

import tk.c4se.halt.ih31.nimunimu.dto.Notification;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.NotificationRepository;

/**
 * @author kei
 *
 */
@WebServlet("/notifications")
public class NotificationsController extends Controller {
	private static final long serialVersionUID = 1L;

	public NotificationsController() {
		super();
		title = "お報せ一覧";
		partial = "/notifications.jsp";
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
		List<Notification> notificationsList = null;
		try {
			notificationsList = new NotificationRepository().all();
		} catch (DBAccessException e) {
			e.printStackTrace();
		}
		req.setAttribute("notificationsList", notificationsList);
		forward(req, resp);
	}
}