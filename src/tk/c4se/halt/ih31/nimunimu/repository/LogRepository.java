/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

import lombok.val;
import tk.c4se.halt.ih31.nimunimu.config.DBConnector;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.Log;
import tk.c4se.halt.ih31.nimunimu.model.Ltsv;

/**
 * @author ne_Sachirou
 */
public class LogRepository implements Serializable {
	private static final long serialVersionUID = -652895656465697295L;

	/**
	 * 
	 * @param log
	 * @throws DBAccessException
	 */
	public void insert(Log log) throws DBAccessException {
		val sql = "insert into log (log) values (?)";
		try (Connection connection = DBConnector.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, new Ltsv().append(log.getLog()).toString());
			statement.executeUpdate();
		} catch (SQLException | NamingException e) {
			throw new DBAccessException();
		}
	}
}