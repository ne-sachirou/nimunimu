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
import tk.c4se.halt.ih31.nimunimu.dto.CustomerOrder;
import tk.c4se.halt.ih31.nimunimu.dto.CustomerOrderStatus;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class CustomerOrderRepository extends RdbRepository<CustomerOrder> {
	private static final long serialVersionUID = 1L;

	public CustomerOrderRepository() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public CustomerOrder find(int id) throws DBAccessException {
		if (id == 0) {
			return null;
		}
		val sql = "select * from customer_order where id = ?";
		CustomerOrder order = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				order = new CustomerOrder();
				setProperties(order, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return order;
	}

	public CustomerOrder findByQuotationRequestSheetId(int sheetId)
			throws DBAccessException {
		if (sheetId == 0) {
			return null;
		}
		val sql = "select * from customer_order where quotation_request_sheet_id = ?";
		CustomerOrder order = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, sheetId);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				order = new CustomerOrder();
				setProperties(order, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return order;
	}

	public CustomerOrder findByCustomerOrderSheetId(int sheetId)
			throws DBAccessException {
		if (sheetId == 0) {
			return null;
		}
		val sql = "select * from customer_order where customer_order_sheet_id = ?";
		CustomerOrder order = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, sheetId);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				order = new CustomerOrder();
				setProperties(order, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return order;
	}

	/**
	 * 
	 * @param idStr
	 * @return
	 * @throws DBAccessException
	 */
	public CustomerOrder find(String idStr) throws DBAccessException {
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
	public List<CustomerOrder> all(int page) throws DBAccessException {
		val sql = "select * from customer_order where rownum between ? and ?";
		List<CustomerOrder> orders = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				CustomerOrder order = new CustomerOrder();
				setProperties(order, result);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return orders;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<CustomerOrder> all() throws DBAccessException {
		val sql = "select * from customer_order";
		List<CustomerOrder> orders = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				CustomerOrder order = new CustomerOrder();
				setProperties(order, result);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return orders;
	}

	public List<CustomerOrder> allByMemberId(int memberId)
			throws DBAccessException {
		val sql = "select * from customer_order where member_id = ?";
		List<CustomerOrder> orders = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, memberId);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				CustomerOrder order = new CustomerOrder();
				setProperties(order, result);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return orders;
	}

	public void insert(CustomerOrder order) throws DBAccessException {
		val sql = "insert into customer_order(id, customer_id, quotation_request_sheet_id, customer_order_sheet_id, member_id, status) values (customer_order_pk_seq.nextval, ?, ?, ?, ?, ?)";
		val sql2 = "select customer_order_pk_seq.currval from dual";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, order.getCustomerId());
			statement.setInt(2, order.getQuotationRequestSheetId());
			statement.setInt(3, order.getCustomerOrderSheetId());
			statement.setString(4, order.getMemberId());
			statement.setString(5, order.getStatus().toString());
			statement.executeUpdate();
			statement = connection.prepareStatement(sql2);
			@Cleanup
			ResultSet result = statement.executeQuery();
			result.next();
			order.setId(result.getInt(1));
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

	public void update(CustomerOrder order) throws DBAccessException {
		val sql = "update customer_order set customer_id = ?, quotation_request_sheet_id = ?, customer_order_sheet_id = ?, member_id = ?, status = ? where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, order.getCustomerId());
			statement.setInt(2, order.getQuotationRequestSheetId());
			statement.setInt(3, order.getCustomerOrderSheetId());
			statement.setString(4, order.getMemberId());
			statement.setString(5, order.getStatus().toString());
			statement.setInt(6, order.getId());
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

	public void delete(CustomerOrder order) throws DBAccessException {
		val sql = "delete from customer_order where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, order.getId());
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
	protected CustomerOrder setProperties(CustomerOrder order, ResultSet result)
			throws SQLException {
		order.setId(result.getInt("id"));
		order.setCustomerId(result.getInt("customer_id"));
		order.setQuotationRequestSheetId(result
				.getInt("quotation_request_sheet_id"));
		order.setCustomerOrderSheetId(result.getInt("customer_order_sheet_id"));
		order.setMemberId(result.getString("member_id"));
		order.setStatus(CustomerOrderStatus.valueOf(result.getString("status")));
		return order;
	}
}
