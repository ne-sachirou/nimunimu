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
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.Member;
import tk.c4se.halt.ih31.nimunimu.model.MemberAuthority;
import tk.c4se.halt.ih31.nimunimu.repository.MemberRepository;

/**
 * @author ne_Sachirou
 */
@WebServlet("/admin/member")
public class AdminMemberController extends Controller {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		val id = req.getParameter("id");
		@val
		Member member;
		try {
			member = new MemberRepository().find(id);
		} catch (DBAccessException e) {
			e.printStackTrace();
			member = null;
		}
		req.setAttribute("member", member);
		forward(req, resp, "admin / member",
				"/resource/partial/admin/member.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		val requestType = req.getParameter("requestType");
		val id = req.getParameter("id");
		val repo = new MemberRepository();
		Member member = null;
		try {
			member = repo.find(id);
		} catch (DBAccessException e2) {
			e2.printStackTrace();
			resp.sendError(502, e2.getMessage());
			return;
		}
		if (member == null) {
			resp.sendError(502, "Member " + id + " is not found in DB.");
			return;
		}
		if (requestType.equals("PUT")) {
			member.setName(req.getParameter("name"));
			member.setAuthority(MemberAuthority.valueOf(req
					.getParameter("authority")));
			try {
				repo.update(member);
			} catch (DBAccessException e1) {
				e1.printStackTrace();
				resp.sendError(502, e1.getMessage());
				return;
			}
		} else if (requestType.equals("DELETE")) {
			try {
				repo.delete(member);
			} catch (DBAccessException e) {
				e.printStackTrace();
				resp.sendError(502, e.getMessage());
				return;
			}
		} else {
			resp.sendError(502, "Unknown request type: " + requestType);
			return;
		}
		forward(req, resp, "admin / member",
				"/resource/partial/admin/member.jsp");
	}
}