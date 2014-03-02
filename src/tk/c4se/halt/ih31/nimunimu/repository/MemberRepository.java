/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Cleanup;
import lombok.val;
import tk.c4se.halt.ih31.nimunimu.config.DBConnector;
import tk.c4se.halt.ih31.nimunimu.dto.Member;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 */
public class MemberRepository extends RdbRepository<Member> {
	private static final long serialVersionUID = 1L;

	public MemberRepository() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public Member find(String id) throws DBAccessException {
		if (id == null || id.isEmpty()) {
			return null;
		}
		val sql = "select * from member where id = ?";
		Member member = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				member = new Member();
				setProperties(member, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return member;
	}

	/**
	 * 
	 * @param page
	 * @return
	 * @throws DBAccessException
	 */
	public List<Member> all(int page) throws DBAccessException {
		val sql = "select * from member where rownum between ? and ?";
		List<Member> members = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				Member member = new Member();
				setProperties(member, result);
				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return members;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<Member> all() throws DBAccessException {
		return all(1);
	}

	/**
	 * 
	 * @param member
	 * @throws DBAccessException
	 */
	public void insert(Member member) throws DBAccessException {
		val sql = "insert into member(id, name, password, is_password_resetted, authority) values (?, ?, ?, ?, ?)";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, member.getId());
			statement.setString(2, member.getName());
			statement.setString(3, member.getPassword());
			statement.setInt(4, member.getIsPasswordReseted() ? 1 : 0);
			statement.setString(5, member.getAuthority().toString());
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					throw new DBAccessException(e);
				}
			}
			throw new DBAccessException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DBAccessException(e);
				}
			}
		}
	}

	/**
	 * 
	 * @param member
	 * @throws DBAccessException
	 */
	public void update(Member member) throws DBAccessException {
		val sql = "update member set name = ?, password = ?, is_password_resetted = ?, authority = ? where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, member.getName());
			statement.setString(2, member.getPassword());
			statement.setInt(3, member.getIsPasswordReseted() ? 1 : 0);
			statement.setString(4, member.getAuthority().toString());
			statement.setString(5, member.getId());
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					throw new DBAccessException(e1);
				}
			}
			throw new DBAccessException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DBAccessException(e);
				}
			}
		}
	}

	/**
	 * 
	 * @param member
	 * @throws DBAccessException
	 */
	public void delete(Member member) throws DBAccessException {
		val sql = "delete from member where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, member.getId());
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					throw new DBAccessException(e1);
				}
			}
			throw new DBAccessException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DBAccessException(e);
				}
			}
		}
	}

	@Override
	protected Member setProperties(Member member, ResultSet result)
			throws SQLException {
		member.setId(result.getString("id"));
		member.setName(result.getString("name"));
		member.setPassword(result.getString("password"));
		member.setIsPasswordReseted(result.getInt("is_password_resetted") != 0);
		member.setAuthority(result.getString("authority"));
		return member;
	}
}