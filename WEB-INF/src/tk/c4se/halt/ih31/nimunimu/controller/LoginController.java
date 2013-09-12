/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.val;

import tk.c4se.hal.ih31.nimunimu.validator.LoginValidator;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.Member;

/**
 * @author ne_Sachirou
 * 
 */
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 41306642246590835L;

	private static final String JSP_PATH = "/jsp/login.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher(JSP_PATH).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		Map<String, Exception> errors = new LoginValidator().validate(req);
		if (!errors.isEmpty()) {
			showError(req, resp, errors);
			return;
		}
		val id = req.getParameter("id").trim();
		val password = req.getParameter("password").trim();
		Boolean isCorrectPassword = false;
		try {
			isCorrectPassword = Member.isCorrectPassword(id, password);
		} catch (DBAccessException e) {
			errors.put("DBAccess", e);
		}
		if (!isCorrectPassword)
			errors.put("Login", new Exception("IDかパスワードが異なります。"));
		if (!errors.isEmpty()) {
			showError(req, resp, errors);
			return;
		}
		HttpSession session = req.getSession();
		session.setAttribute("memberId", id);
		resp.sendRedirect("/");
	}

	private void showError(HttpServletRequest req, HttpServletResponse resp,
			Map<String, Exception> errors) throws ServletException, IOException {
		val id = req.getParameter("id");
		val password = req.getParameter("password");
		req.setAttribute("id", id);
		req.setAttribute("password", password);
		req.setAttribute("errors", errors);
		req.getRequestDispatcher(JSP_PATH).forward(req, resp);
	}
}
