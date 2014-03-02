/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.c4se.halt.ih31.nimunimu.repository.SessionRepository;

/**
 * @author ne_Sachirou
 */
@WebServlet("/logout")
public class LogoutController extends Controller {
	private static final long serialVersionUID = 1L;

	public LogoutController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
		new SessionRepository(req).setLoginAccountId(null);
		resp.sendRedirect(req.getSession().getServletContext().getContextPath()
				+ "/");
	}
}