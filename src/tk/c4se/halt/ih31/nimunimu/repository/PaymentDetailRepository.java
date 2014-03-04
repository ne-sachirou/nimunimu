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
import tk.c4se.halt.ih31.nimunimu.dto.PaymentDetail;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class PaymentDetailRepository extends RdbRepository<PaymentDetail> {
	private static final long serialVersionUID = 1L;

	public PaymentDetailRepository() {
		super();
	}

	/**
	 * 
	 * @param paymentId
	 * @return
	 * @throws DBAccessException
	 */
	public PaymentDetail find(int paymentId, int ourOrderId)
			throws DBAccessException {
		if (paymentId == 0) {
			return null;
		}
		val sql = "select * from payment_detail where payment_id = ? and our_order_id = ?";
		PaymentDetail detail = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, paymentId);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				detail = new PaymentDetail();
				setProperties(detail, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return detail;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<PaymentDetail> all() throws DBAccessException {
		val sql = "select * from payment_detail";
		List<PaymentDetail> details = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				PaymentDetail detail = new PaymentDetail();
				setProperties(detail, result);
				details.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return details;
	}

	public List<PaymentDetail> allByPaymentId(int paymentId)
			throws DBAccessException {
		val sql = "select * from payment_detail where payment_id = ?";
		List<PaymentDetail> details = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, paymentId);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				PaymentDetail detail = new PaymentDetail();
				setProperties(detail, result);
				details.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return details;
	}

	public List<PaymentDetail> allByOurOuderId(int ourOrderId)
			throws DBAccessException {
		val sql = "select * from payment_detail where our_order_id = ?";
		List<PaymentDetail> details = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, ourOrderId);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				PaymentDetail detail = new PaymentDetail();
				setProperties(detail, result);
				details.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return details;
	}

	public void insert(PaymentDetail detail) throws DBAccessException {
		val sql = "insert into payment_detail(payment_id, our_ouder_id) values (?, ?)";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, detail.getPaymentId());
			statement.setInt(2, detail.getOurOrderId());
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

	public void update(PaymentDetail detail) throws DBAccessException {
		throw new DBAccessException("We cannot update PaymentDetail.");
	}

	public void delete(PaymentDetail detail) throws DBAccessException {
		val sql = "delete from payment_detail where payment_id = ? and our_order_id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, detail.getPaymentId());
			statement.setInt(2, detail.getOurOrderId());
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
	protected PaymentDetail setProperties(PaymentDetail detail, ResultSet result)
			throws SQLException {
		detail.setPaymentId(result.getInt("payment_id"));
		detail.setOurOrderId(result.getInt("our_order_id"));
		return detail;
	}
}
