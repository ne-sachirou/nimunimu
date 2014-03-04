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
import tk.c4se.halt.ih31.nimunimu.dto.Payment;
import tk.c4se.halt.ih31.nimunimu.dto.PaymentStatus;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class PaymentRepository extends RdbRepository<Payment> {
	private static final long serialVersionUID = 1L;

	public PaymentRepository() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public Payment find(int id) throws DBAccessException {
		if (id == 0) {
			return null;
		}
		val sql = "select * from payment where id = ?";
		Payment payment = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				payment = new Payment();
				setProperties(payment, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return payment;
	}

	/**
	 * 
	 * @param idStr
	 * @return
	 * @throws DBAccessException
	 */
	public Payment find(String idStr) throws DBAccessException {
		final int id;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			return null;
		}
		return find(id);
	}

	/**
	 * 
	 * @param page
	 * @return
	 * @throws DBAccessException
	 */
	public List<Payment> all(int page) throws DBAccessException {
		val sql = "select * from payment where rownum between ? and ?";
		List<Payment> payments = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				Payment payment = new Payment();
				setProperties(payment, result);
				payments.add(payment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return payments;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<Payment> all() throws DBAccessException {
		val sql = "select * from payment ?";
		List<Payment> payments = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				Payment payment = new Payment();
				setProperties(payment, result);
				payments.add(payment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return payments;
	}

	public void insert(Payment payment) throws DBAccessException {
		val sql = "insert into payment(id, supplier_id, member_id, status) values (payment_pk_seq.nextval, ?, ?, ?)";
		val sql2 = "select payment_pk_seq.currval from dual";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, payment.getSupplierId());
			statement.setString(2, payment.getMemberId());
			statement.setString(3, payment.getStatus().toString());
			statement.executeUpdate();
			statement = connection.prepareStatement(sql2);
			@Cleanup
			ResultSet result = statement.executeQuery();
			result.next();
			payment.setId(result.getInt(1));
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

	public void update(Payment payment) throws DBAccessException {
		val sql = "update payment set supplier_id = ?, member_id = ?, status = ? where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, payment.getSupplierId());
			statement.setString(2, payment.getMemberId());
			statement.setString(3, payment.getStatus().toString());
			statement.setInt(4, payment.getId());
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

	public void delete(Payment payment) throws DBAccessException {
		val sql = "delete from payment where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, payment.getId());
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
	protected Payment setProperties(Payment payment, ResultSet result)
			throws SQLException {
		payment.setId(result.getInt("id"));
		payment.setSupplierId(result.getInt("supplier_id"));
		payment.setMemberId(result.getString("memberId"));
		payment.setStatus(PaymentStatus.valueOf(result.getString("status")));
		return payment;
	}
}
