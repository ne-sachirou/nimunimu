/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.model;

import lombok.Data;
import lombok.val;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.repository.MemberRepository;

/**
 * Member bean.
 * 
 * @author ne_Sachirou
 */
@Data
public class Member implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param id
	 * @param rowPassword
	 * @return
	 * @throws DBAccessException
	 */
	public static Boolean isCorrectPassword(String id, String rawPassword)
			throws DBAccessException {
		val member = new MemberRepository().find(id);
		if (member == null) {
			return false;
		}
		return member.isCorrectPassword(rawPassword);
	}

	private String id;
	private String name;
	private String password;
	private Boolean isPasswordReseted;
	private MemberAuthority authority;

	/**
	 * 
	 * @param rawPassword
	 * @return
	 */
	public Boolean isCorrectPassword(String rawPassword) {
		return getPassword().equals(rawPassword);
	}
}