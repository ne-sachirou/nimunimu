package tk.c4se.halt.ih31.nimunimu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.c4se.halt.ih31.nimunimu.model.Member;

public class IndexController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4945277894364938223L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Member member = (Member) req.getAttribute("currentMember");
		if (member == null) {
			resp.sendRedirect("/login");
			return;
		}
		req.setAttribute("partial", "/jsp/index.jsp");
		req.getRequestDispatcher("/jsp/layout/layout.jsp").forward(req, resp);
	}
}
