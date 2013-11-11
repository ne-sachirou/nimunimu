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

import tk.c4se.halt.ih31.nimunimu.model.Log;
import tk.c4se.halt.ih31.nimunimu.model.Member;

/**
 * @author ne_Sachirou
 * 
 */
public class AccessLogFilter implements java.io.Serializable,
		javax.servlet.Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8966715137100711011L;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		final String uri = ((HttpServletRequest) req).getRequestURI();
		final String method = ((HttpServletRequest) req).getMethod();
		final String memberId = ((Member) req.getAttribute("currentMember"))
				.getId();
		final String protocol = ((HttpServletRequest) req).getProtocol();
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
