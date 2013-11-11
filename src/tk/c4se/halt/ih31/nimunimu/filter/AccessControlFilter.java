/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.Member;
import tk.c4se.halt.ih31.nimunimu.repository.MemberRepository;
import tk.c4se.halt.ih31.nimunimu.repository.SessionRepository;

/**
 * @author ne_Sachirou
 * 
 */
public class AccessControlFilter implements java.io.Serializable,
		javax.servlet.Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6372244373335629676L;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		val session = new SessionRepository()
				.getSeeeion((HttpServletRequest) req);
		String memberId = (String) session.getAttribute("memberId");
		memberId = "AD00001";
		Member member = null;
		try {
			member = new MemberRepository().find(memberId);
		} catch (DBAccessException e) {
			e.printStackTrace();
		}
		req.setAttribute("currentMember", member);
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
