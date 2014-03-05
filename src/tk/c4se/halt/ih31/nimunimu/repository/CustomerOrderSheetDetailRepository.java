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
import tk.c4se.halt.ih31.nimunimu.dto.CustomerOrderSheetDetail;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class CustomerOrderSheetDetailRepository extends
		RdbRepository<CustomerOrderSheetDetail> {
	private static final long serialVersionUID = 1L;

	public CustomerOrderSheetDetailRepository() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public CustomerOrderSheetDetail find(int id) throws DBAccessException {
		if (id == 0) {
			return null;
		}
		val sql = "select * from customer_order_sheet_detail where id = ?";
		CustomerOrderSheetDetail detail = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				detail = new CustomerOrderSheetDetail();
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
	 * @param idStr
	 * @return
	 * @throws DBAccessException
	 */
	public CustomerOrderSheetDetail find(String idStr) throws DBAccessException {
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
	 * @return
	 * @throws DBAccessException
	 */
	public List<CustomerOrderSheetDetail> all() throws DBAccessException {
		val sql = "select * from customer_order_sheet_detail";
		List<CustomerOrderSheetDetail> details = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				CustomerOrderSheetDetail detail = new CustomerOrderSheetDetail();
				setProperties(detail, result);
				details.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return details;
	}

	public List<CustomerOrderSheetDetail> allByCustomerOrderSheetId(
			int ourOrderSheetId) throws DBAccessException {
		val sql = "select * from customer_order_sheet_detail where customer_order_sheet_id = ?";
		List<CustomerOrderSheetDetail> details = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, ourOrderSheetId);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				CustomerOrderSheetDetail detail = new CustomerOrderSheetDetail();
				setProperties(detail, result);
				details.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return details;
	}

	public void insert(CustomerOrderSheetDetail detail)
			throws DBAccessException {
		val sql = "insert into customer_order_sheet_detail(id, customer_order_sheet_id, goods_id, price, goods_number) values (customer_order_sheet_dtl_pk_s.nextval, ?, ?, ?, ?)";
		val sql2 = "select customer_order_sheet_dtl_pk_s.currval from dual";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, detail.getCustomerOrderSheetId());
			statement.setString(2, detail.getGoodsId());
			statement.setInt(3, detail.getPrice());
			statement.setInt(4, detail.getGoodsNumber());
			statement.executeUpdate();
			statement = connection.prepareStatement(sql2);
			@Cleanup
			ResultSet result = statement.executeQuery();
			result.next();
			detail.setId(result.getInt(1));
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

	public void update(CustomerOrderSheetDetail detail)
			throws DBAccessException {
		val sql = "update customer_order_sheet_detail set customer_order_sheet_id = ?, goods_id = ?, price = ?, goods_number = ? where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, detail.getCustomerOrderSheetId());
			statement.setString(2, detail.getGoodsId());
			statement.setInt(3, detail.getPrice());
			statement.setInt(4, detail.getGoodsNumber());
			statement.setInt(5, detail.getId());
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

	public void delete(CustomerOrderSheetDetail detail)
			throws DBAccessException {
		val sql = "delete from customer_order_sheet_detail where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, detail.getId());
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
	protected CustomerOrderSheetDetail setProperties(
			CustomerOrderSheetDetail detail, ResultSet result)
			throws SQLException {
		detail.setId(result.getInt("id"));
		detail.setCustomerOrderSheetId(result.getInt("our_order_sheet_id"));
		detail.setGoodsId(result.getString("goods_id"));
		detail.setPrice(result.getInt("price"));
		detail.setGoodsNumber(result.getInt("goods_number"));
		return detail;
	}
}
