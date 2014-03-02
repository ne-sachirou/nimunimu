/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.c4se.halt.ih31.nimunimu.dto.Member;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.MemberRepository;
import lombok.val;

/**
 * 
 * @author ne_Sachirou
 * 
 */
public class MemberModel implements DoPostModel {
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void postRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val id = req.getParameter("id");
		val repo = new MemberRepository();
		Member member = new Member();
		member.setId(id);
		member.setName(req.getParameter("name"));
		member.setAuthority(req.getParameter("authority"));
		member.setPassword(req.getParameter("password"));
		member.setIsPasswordReseted(false);
		try {
			repo.insert(member);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void putRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val id = req.getParameter("id");
		val repo = new MemberRepository();
		Member member = null;
		try {
			member = repo.find(id);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (member == null) {
			throw new DBAccessException("Member " + id + " is not found in DB.");
		}
		member.setName(req.getParameter("name"));
		member.setAuthority(req.getParameter("authority"));
		try {
			repo.update(member);
		} catch (DBAccessException e1) {
			e1.printStackTrace();
			throw e1;
		}
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws DBAccessException
	 */
	public void deleteRequest(HttpServletRequest req, HttpServletResponse resp)
			throws DBAccessException {
		val id = req.getParameter("id");
		val repo = new MemberRepository();
		Member member = null;
		try {
			member = repo.find(id);
		} catch (DBAccessException e) {
			e.printStackTrace();
			throw e;
		}
		if (member == null) {
			throw new DBAccessException("Member " + id + " is not found in DB.");
		}
		try {
			repo.delete(member);
		} catch (DBAccessException e1) {
			e1.printStackTrace();
			return;
		}
	}
}