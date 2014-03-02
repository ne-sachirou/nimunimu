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
import tk.c4se.halt.ih31.nimunimu.dto.Member;
import tk.c4se.halt.ih31.nimunimu.dto.MemberAuthority;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.MemberModel;
import tk.c4se.halt.ih31.nimunimu.repository.MemberRepository;

/**
 * @author ne_Sachirou
 */
@WebServlet("/admin/member")
public class AdminMemberController extends Controller {
	private static final long serialVersionUID = 1L;

	public AdminMemberController() {
		super();
		title = "社員アカウント詳細";
		partial = "/admin/member.jsp";
		authorities.add(MemberAuthority.ADMIN);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
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
		forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		val model = new MemberModel();
		try {
			processDoPostRequest(req, resp, model);
		} catch (IOException e) {
			resp.sendError(502, e.getMessage());
			return;
		}
		forward(req, resp);
	}
}