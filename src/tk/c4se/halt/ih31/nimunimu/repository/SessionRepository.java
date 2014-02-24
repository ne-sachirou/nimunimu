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
	private static final long serialVersionUID = 1L;

	private HttpSession session;

	public SessionRepository() {
	}

	public SessionRepository(HttpServletRequest req) {
		session = req.getSession();
	}

	/**
	 * 
	 * @return
	 */
	public String getLoginAccountId() {
		return (String) session.getAttribute("loginAccountId");
	}

	/**
	 * 
	 * @param accountId
	 */
	public void setLoginAccountId(String accountId) {
		session.setAttribute("loginAccountId", accountId);
	}

	/**
	 * 
	 * @return
	 */
	public String getCsrfToken() {
		if (session.getAttribute("csrf") == null) {
			session.setAttribute("csrf", UUID.randomUUID().toString());
		}
		return (String) session.getAttribute("csrf");
	}
}