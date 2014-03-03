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
import tk.c4se.halt.ih31.nimunimu.dto.Supplier;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class SupplierRepository extends RdbRepository<Supplier> {
	private static final long serialVersionUID = 1L;

	public SupplierRepository() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public Supplier find(int id) throws DBAccessException {
		if (id == 0) {
			return null;
		}
		val sql = "select * from supplier where id = ?";
		Supplier supplier = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				supplier = new Supplier();
				setProperties(supplier, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return supplier;
	}

	/**
	 * 
	 * @param idStr
	 * @return
	 * @throws DBAccessException
	 */
	public Supplier find(String idStr) throws DBAccessException {
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
	public List<Supplier> all(int page) throws DBAccessException {
		val sql = "select * from supplier where rownum between ? and ?";
		List<Supplier> suppliers = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				Supplier supplier = new Supplier();
				setProperties(supplier, result);
				suppliers.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return suppliers;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<Supplier> all() throws DBAccessException {
		return all(1);
	}

	public void insert(Supplier supplier) throws DBAccessException {
		val sql = "insert into supplier(id, name, zipcode, address, tel, fax, preson, billing_cutoff_date) values (supplier_pk_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, supplier.getName());
			statement.setString(2, supplier.getZipcode());
			statement.setString(3, supplier.getAddress());
			statement.setString(4, supplier.getTel());
			statement.setString(5, supplier.getFax());
			statement.setString(6, supplier.getPerson());
			statement.setInt(7, supplier.getBillingCutoffDate());
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

	public void update(Supplier supplier) throws DBAccessException {
		val sql = "update supplier set name = ?, zipcode = ?, address = ?, tel = ?, fax = ?, person = ?, billing_cutoff_date = ? where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, supplier.getName());
			statement.setString(2, supplier.getZipcode());
			statement.setString(3, supplier.getAddress());
			statement.setString(4, supplier.getTel());
			statement.setString(5, supplier.getFax());
			statement.setString(6, supplier.getPerson());
			statement.setInt(7, supplier.getBillingCutoffDate());
			statement.setInt(8, supplier.getId());
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

	public void delete(Supplier supplier) throws DBAccessException {
		val sql = "delete from supplier where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, supplier.getId());
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
	protected Supplier setProperties(Supplier supplier, ResultSet result)
			throws SQLException {
		supplier.setId(result.getInt("id"));
		supplier.setName(result.getString("name"));
		supplier.setZipcode(result.getString("zipcode"));
		supplier.setAddress(result.getString("address"));
		supplier.setTel(result.getString("tel"));
		supplier.setFax(result.getString("fax"));
		supplier.setPerson(result.getString("person"));
		supplier.setBillingCutoffDate(result.getInt("billing_cutoff_date"));
		return supplier;
	}
}
