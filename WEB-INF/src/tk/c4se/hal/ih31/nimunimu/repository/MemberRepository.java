/**
 * 
 */
package tk.c4se.hal.ih31.nimunimu.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Cleanup;
import lombok.val;

import tk.c4se.hal.ih31.nimunimu.config.DBConfig;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.Member;

/**
 * @author ne_Sachirou
 * 
 */
public class MemberRepository implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -990443787408704909L;

	private static final String tableName = "member";

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public Member find(String id) throws DBAccessException {
		if (id == null || id.isEmpty())
			return null;
		val sql = "select * from " + tableName + " where id = ?";
		Member member = null;
		try (Connection connection = DBConfig.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			@Cleanup
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				member = new Member();
				setProperties(member, result);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBAccessException();
		}
		return member;
	}

	/**
	 * 
	 * @param member
	 */
	public void insert(Member member) {

	}

	/**
	 * 
	 * @param member
	 */
	public void update(Member member) {

	}

	/**
	 * 
	 * @param member
	 */
	public void delete(Member member) {

	}

	private void setProperties(Member member, ResultSet result)
			throws SQLException {
		member.setId(result.getString("id"));
	}
}
