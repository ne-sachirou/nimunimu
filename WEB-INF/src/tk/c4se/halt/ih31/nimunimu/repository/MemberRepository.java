/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Cleanup;
import lombok.val;
import tk.c4se.halt.ih31.nimunimu.config.DBConfig;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;
import tk.c4se.halt.ih31.nimunimu.model.Member;
import tk.c4se.halt.ih31.nimunimu.model.MemberAuthority;

/**
 * @author ne_Sachirou
 * 
 */
public class MemberRepository implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -990443787408704909L;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public Member find(String id) throws DBAccessException {
		Member member = new Member();
		member.setId("AD00001");
		member.setIsPasswordResetted(false);
		member.setAuthority(MemberAuthority.ADMIN);
		return member;
		/*
		 * if (id == null || id.isEmpty()) return null; val sql =
		 * "select * from member where id = ?"; Member member = null; try (val
		 * connection = DBConfig.getConnection()) { PreparedStatement statement
		 * = connection.prepareStatement(sql); statement.setString(1, id);
		 * 
		 * @Cleanup val result = statement.executeQuery(); if (result.next()) {
		 * member = new Member(); setProperties(member, result); } } catch
		 * (ClassNotFoundException | SQLException e) { throw new
		 * DBAccessException(); } return member;
		 */
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
		member.setPassword(result.getString("password"));
		member.setSalt(result.getString("salt"));
		member.setIsPasswordResetted(result.getInt("is_password_resetted") == 0 ? false
				: true);
		member.setAuthority(MemberAuthority.valueOf(result
				.getString("authority")));
		member.setCreatedAt(result.getTimestamp("created_at"));
		member.setUpdatedAt(result.getTimestamp("updated_at"));
		member.setDeletedAt(result.getTimestamp("deleted_at"));
	}
}
