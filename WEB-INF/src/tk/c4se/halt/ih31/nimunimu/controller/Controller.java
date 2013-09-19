/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.model.Member;
import tk.c4se.halt.ih31.nimunimu.model.MemberAuthority;
import tk.c4se.halt.ih31.nimunimu.repository.SessionRepository;

/**
 * @author ne_Sachirou
 * 
 */
public abstract class Controller extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8093435378978911657L;

	protected List<MemberAuthority> authorities = new ArrayList<>();

	/**
	 * 
	 * @param req
	 * @param resp
	 * @param title
	 *            Page title.
	 * @param partial
	 *            Partial JSP path.
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void forward(HttpServletRequest req, HttpServletResponse resp,
			String title, String partial) throws ServletException, IOException {
		req.setAttribute("title", title);
		req.setAttribute("partial", partial);
		req.getRequestDispatcher("/jsp/layout/layout.jsp").forward(req, resp);
	}

	/**
	 * Check the current Member is authorized to access the page, and auto
	 * redirect to login page.
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	protected boolean checkAuthorized(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		val is_auth = isAuthorized(req);
		if (!is_auth) {
			val url = URLEncoder.encode(req.getRequestURI(), "utf-8");
			resp.sendRedirect("/login?redirect=" + url);
		}
		return is_auth;
	}

	/**
	 * Check CSRF protection ,and auto return an error when the token is
	 * invalid.
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	protected boolean checkCsrf(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		val session = new SessionRepository().getSeeeion(req);
		val storedToken = (String) session.getAttribute("csrf");
		val receivedToken = (String) req.getAttribute("csrf");
		if (storedToken.equals(receivedToken))
			return true;
		else {
			resp.sendError(403, "Invalid CSRF token.");
			return false;
		}
	}

	private boolean isAuthorized(HttpServletRequest req) {
		val currentMember = (Member) req.getAttribute("currentMember");
		if (authorities.size() == 0)
			return true;
		if (currentMember == null)
			return false;
		for (val auth : authorities) {
			if (auth.equals(currentMember.getAuthority()))
				return true;
		}
		return false;
	}
}
