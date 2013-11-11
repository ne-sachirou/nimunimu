/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.validator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.val;

import tk.c4se.halt.ih31.nimunimu.exception.ValidateException;

/**
 * @author ne_Sachirou
 * 
 */
public class LoginValidator implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2314986808736644911L;

	public Map<String, Exception> validate(HttpServletRequest req) {
		val id = req.getParameter("id").trim();
		val password = req.getParameter("password").trim();
		Map<String, Exception> errors = new HashMap<>();
		if (id == null || id.isEmpty())
			errors.put("Validate", new ValidateException("IDを入力してください。"));
		if (password == null || password.isEmpty())
			errors.put("Validate", new ValidateException("パスワードを入力してください。"));
		return errors;
	}
}
