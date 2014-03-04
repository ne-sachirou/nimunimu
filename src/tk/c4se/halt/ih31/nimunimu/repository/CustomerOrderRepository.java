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
		CustomerOrder customerOrder = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				customerOrder = new CustomerOrder();
				setProperties(customerOrder, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return customerOrder;
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
		List<CustomerOrder> customerOrders = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				CustomerOrder customerOrder = new CustomerOrder();
				setProperties(customerOrder, result);
				customerOrders.add(customerOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return customerOrders;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<CustomerOrder> all() throws DBAccessException {
		val sql = "select * from customer_order";
		List<CustomerOrder> customerOrders = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				CustomerOrder customerOrder = new CustomerOrder();
				setProperties(customerOrder, result);
				customerOrders.add(customerOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return customerOrders;
	}

	public List<CustomerOrder> allByMemberId(int memberId)
			throws DBAccessException {
		val sql = "select * from customer_order where member_id = ?";
		List<CustomerOrder> customerOrders = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, memberId);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				CustomerOrder customerOrder = new CustomerOrder();
				setProperties(customerOrder, result);
				customerOrders.add(customerOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return customerOrders;
	}

	public void insert(CustomerOrder customerOrder) throws DBAccessException {
		val sql = "insert into customer_order(id, customer_id, quotation_request_sheet_id, customer_order_sheet_id, member_id, status) values (customer_order_pk_seq.nextval, ?, ?, ?, ?, ?)";
		val sql2 = "select customer_order_pk_seq.currval from dual";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerOrder.getCustomerId());
			statement.setInt(2, customerOrder.getQuotationRequestSheetId());
			statement.setInt(3, customerOrder.getCustomerOrderSheetId());
			statement.setString(4, customerOrder.getMemberId());
			statement.setString(5, customerOrder.getStatus().toString());
			statement.executeUpdate();
			statement = connection.prepareStatement(sql2);
			@Cleanup
			ResultSet result = statement.executeQuery();
			result.next();
			customerOrder.setId(result.getInt(1));
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

	public void update(CustomerOrder customerOrder) throws DBAccessException {
		val sql = "update customer_order set customer_id = ?, quotation_request_sheet_id = ?, customer_order_sheet_id = ?, member_id = ?, status = ? where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerOrder.getCustomerId());
			statement.setInt(2, customerOrder.getQuotationRequestSheetId());
			statement.setInt(3, customerOrder.getCustomerOrderSheetId());
			statement.setString(4, customerOrder.getMemberId());
			statement.setString(5, customerOrder.getStatus().toString());
			statement.setInt(6, customerOrder.getId());
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

	public void delete(CustomerOrder customerOrder) throws DBAccessException {
		val sql = "delete from customer_order where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerOrder.getId());
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
	protected CustomerOrder setProperties(CustomerOrder customerOrder,
			ResultSet result) throws SQLException {
		customerOrder.setId(result.getInt("id"));
		customerOrder.setCustomerId(result.getInt("customer_id"));
		customerOrder.setQuotationRequestSheetId(result
				.getInt("quotation_request_sheet_id"));
		customerOrder.setCustomerOrderSheetId(result
				.getInt("customer_order_sheet_id"));
		customerOrder.setMemberId(result.getString("member_id"));
		customerOrder.setStatus(CustomerOrderStatus.valueOf(result
				.getString("status")));
		return customerOrder;
	}
}
