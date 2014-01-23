/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.repository;

import java.io.Serializable;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ne_Sachirou
 */
public class SessionRepository implements Serializable {
	private static final long serialVersionUID = -1733126608931382796L;

	/**
	 * 
	 * @param req
	 * @param isNew
	 * @return
	 */
	public HttpSession getSession(HttpServletRequest req, boolean isNew) {
		final HttpSession session = req.getSession();
		if (isNew) {
			session.invalidate();
		}
		if (isNew || session.getAttribute("csrf") == null) {
			session.setAttribute("csrf", UUID.randomUUID().toString());
		}
		return session;
	}

	/**
	 * 
	 * @param req
	 * @return
	 */
	public HttpSession getSeeeion(HttpServletRequest req) {
		return getSession(req, false);
	}
}