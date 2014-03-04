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
import tk.c4se.halt.ih31.nimunimu.dto.Member;
import tk.c4se.halt.ih31.nimunimu.dto.MemberAuthority;
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
		title = "おしらせ一覧";
		partial = "/notifications.jsp";
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
		val loginMember = (Member) req.getAttribute("loginMember");
		List<Notification> notifications = null;
		try {
			notifications = new NotificationRepository()
					.allByMemberId(loginMember.getId());
		} catch (DBAccessException e) {
			e.printStackTrace();
		}
		req.setAttribute("notifications", notifications);
		forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		val id = Integer.parseInt(req.getParameter("id"));
		val repo = new NotificationRepository();
		@val
		Notification notification;
		try {
			notification = repo.find(id);
			repo.delete(notification);
		} catch (DBAccessException e) {
			e.printStackTrace();
			resp.sendError(502, e.getMessage());
			return;
		}
		forward(req, resp);
	}
}