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
import tk.c4se.halt.ih31.nimunimu.dto.Customer;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class CustomerRepository extends RdbRepository<Customer> {
	private static final long serialVersionUID = 1L;

	public CustomerRepository() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public Customer find(int id) throws DBAccessException {
		if (id == 0) {
			return null;
		}
		val sql = "select * from customer where id = ?";
		Customer customer = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				customer = new Customer();
				setProperties(customer, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException();
		}
		return customer;
	}

	/**
	 * 
	 * @param idStr
	 * @return
	 * @throws DBAccessException
	 */
	public Customer find(String idStr) throws DBAccessException {
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
	public List<Customer> all(int page) throws DBAccessException {
		val sql = "select * from customer where rownum between ? and ?";
		List<Customer> customers = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				Customer customer = new Customer();
				setProperties(customer, result);
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException();
		}
		return customers;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<Customer> all() throws DBAccessException {
		return all(1);
	}

	public void insert(Customer customer) throws DBAccessException {
		val sql = "insert into customer(id, name, zipcode, address, tel, fax, preson, billing_cutoff_date, credit_limit) values (goods_category_pk_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, customer.getName());
			statement.setString(2, customer.getZipcode());
			statement.setString(3, customer.getAddress());
			statement.setString(4, customer.getTel());
			statement.setString(5, customer.getFax());
			statement.setString(6, customer.getPerson());
			statement.setInt(7, customer.getBillingCutoffDate());
			statement.setInt(8, customer.getCreditLimit());
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

	public void update(Customer customer) throws DBAccessException {
		val sql = "update goods_category set name = ?, zipcode = ?, address = ?, tel = ?, fax = ?, person = ?, billing_cutoff_date, credit_limit = ? where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, customer.getName());
			statement.setString(2, customer.getZipcode());
			statement.setString(3, customer.getAddress());
			statement.setString(4, customer.getTel());
			statement.setString(5, customer.getFax());
			statement.setString(6, customer.getPerson());
			statement.setInt(7, customer.getBillingCutoffDate());
			statement.setInt(8, customer.getCreditLimit());
			statement.setInt(9, customer.getId());
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

	public void delete(Customer customer) throws DBAccessException {
		val sql = "delete from customer where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customer.getId());
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
	protected Customer setProperties(Customer customer, ResultSet result)
			throws SQLException {
		customer.setId(result.getInt("id"));
		customer.setName(result.getString("name"));
		customer.setZipcode(result.getString("zipcode"));
		customer.setAddress(result.getString("address"));
		customer.setTel(result.getString("tel"));
		customer.setFax(result.getString("fax"));
		customer.setPerson(result.getString("person"));
		customer.setBillingCutoffDate(result.getInt("billing_cutoff_date"));
		customer.setCreditLimit(result.getInt("credit_limit"));
		return customer;
	}
}
