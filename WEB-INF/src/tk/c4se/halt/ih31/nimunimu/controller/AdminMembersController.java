/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.repository.MemberRepository;

/**
 * @author ne_Sachirou
 * 
 */
public class AdminMembersController extends Controller {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8637277308340058722L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		val members = new MemberRepository().all();
		req.setAttribute("members", members);
		forward(req, resp, "admin/members", "/jsp/admin/members.jsp");
	}
}