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
import tk.c4se.halt.ih31.nimunimu.dto.CustomerOrderSheet;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class CustomerOrderSheetRepository extends
		RdbRepository<CustomerOrderSheet> {
	private static final long serialVersionUID = 1L;

	public CustomerOrderSheetRepository() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public CustomerOrderSheet find(int id) throws DBAccessException {
		if (id == 0) {
			return null;
		}
		val sql = "select * from customer_order_sheet where id = ?";
		CustomerOrderSheet sheet = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				sheet = new CustomerOrderSheet();
				setProperties(sheet, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return sheet;
	}

	/**
	 * 
	 * @param idStr
	 * @return
	 * @throws DBAccessException
	 */
	public CustomerOrderSheet find(String idStr) throws DBAccessException {
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
	public List<CustomerOrderSheet> all(int page) throws DBAccessException {
		val sql = "select * from customer_order_sheet where deleted_at = null and rownum between ? and ?";
		List<CustomerOrderSheet> sheets = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				CustomerOrderSheet sheet = new CustomerOrderSheet();
				setProperties(sheet, result);
				sheets.add(sheet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return sheets;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<CustomerOrderSheet> all() throws DBAccessException {
		val sql = "select * from customer_order_sheet where deleted_at = null";
		List<CustomerOrderSheet> sheets = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				CustomerOrderSheet sheet = new CustomerOrderSheet();
				setProperties(sheet, result);
				sheets.add(sheet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return sheets;
	}

	public void insert(CustomerOrderSheet sheet) throws DBAccessException {
		val sql = "insert into customer_order_sheet(id, amount, tax) values (customer_order_sheet_pk_seq.nextval, ?, ?)";
		val sql2 = "select customer_order_sheet_pk_seq.currval from dual";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, sheet.getAmount());
			statement.setInt(2, sheet.getTax());
			statement.executeUpdate();
			statement = connection.prepareStatement(sql2);
			@Cleanup
			ResultSet result = statement.executeQuery();
			result.next();
			sheet.setId(result.getInt(1));
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

	public void update(CustomerOrderSheet sheet) throws DBAccessException {
		val sql = "update customer_order_sheet set amount = ?, tax = ?, updated_at = systimestamp where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, sheet.getAmount());
			statement.setInt(2, sheet.getTax());
			statement.setInt(3, sheet.getId());
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

	public void delete(CustomerOrderSheet sheet) throws DBAccessException {
		val sql = "update customer_order_sheet set deleted_at = systimestamp where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, sheet.getId());
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
	protected CustomerOrderSheet setProperties(CustomerOrderSheet sheet,
			ResultSet result) throws SQLException {
		sheet.setId(result.getInt("id"));
		sheet.setAmount(result.getInt("amount"));
		sheet.setTax(result.getInt("tax"));
		sheet.setCreatedAt(result.getDate("created_at"));
		sheet.setUpdatedAt(result.getDate("updated_at"));
		sheet.setDeletedAt(result.getDate("deleted_at"));
		return sheet;
	}
}
