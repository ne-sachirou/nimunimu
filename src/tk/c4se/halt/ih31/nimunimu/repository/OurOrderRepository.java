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
import tk.c4se.halt.ih31.nimunimu.dto.OurOrder;
import tk.c4se.halt.ih31.nimunimu.dto.OurOrderStatus;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class OurOrderRepository extends RdbRepository<OurOrder> {
	private static final long serialVersionUID = 1L;

	public OurOrderRepository() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public OurOrder find(int id) throws DBAccessException {
		if (id == 0) {
			return null;
		}
		val sql = "select * from our_order where id = ?";
		OurOrder ourOrder = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				ourOrder = new OurOrder();
				setProperties(ourOrder, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return ourOrder;
	}

	/**
	 * 
	 * @param idStr
	 * @return
	 * @throws DBAccessException
	 */
	public OurOrder find(String idStr) throws DBAccessException {
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
	public List<OurOrder> all(int page) throws DBAccessException {
		val sql = "select * from our_order where rownum between ? and ?";
		List<OurOrder> ourOrders = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				OurOrder ourOrder = new OurOrder();
				setProperties(ourOrder, result);
				ourOrders.add(ourOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return ourOrders;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<OurOrder> all() throws DBAccessException {
		val sql = "select * from our_order ?";
		List<OurOrder> ourOrders = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				OurOrder ourOrder = new OurOrder();
				setProperties(ourOrder, result);
				ourOrders.add(ourOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return ourOrders;
	}

	public void insert(OurOrder ourOrder) throws DBAccessException {
		val sql = "insert into our_order(id, supplier_id, our_order_sheet_id, member_id, status) values (our_order_pk_seq.nextval, ?, ?, ?, ?)";
		val sql2 = "select our_order_pk_seq.currval from dual";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, ourOrder.getSupplierId());
			statement.setInt(2, ourOrder.getOurOrderSheetId());
			statement.setString(3, ourOrder.getMemberId());
			statement.setString(4, ourOrder.getStatus().toString());
			statement.executeUpdate();
			statement = connection.prepareStatement(sql2);
			@Cleanup
			ResultSet result = statement.executeQuery();
			result.next();
			ourOrder.setId(result.getInt(1));
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

	public void update(OurOrder ourOrder) throws DBAccessException {
		val sql = "update our_order set supplier_id = ?, our_order_sheet_id = ?, member_id = ?, status = ? where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, ourOrder.getSupplierId());
			statement.setInt(2, ourOrder.getOurOrderSheetId());
			statement.setString(3, ourOrder.getMemberId());
			statement.setString(4, ourOrder.getStatus().toString());
			statement.setInt(5, ourOrder.getId());
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

	public void delete(OurOrder ourOrder) throws DBAccessException {
		val sql = "delete from our_order where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, ourOrder.getId());
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
	protected OurOrder setProperties(OurOrder ourOrder, ResultSet result)
			throws SQLException {
		ourOrder.setId(result.getInt("id"));
		ourOrder.setSupplierId(result.getInt("supplier_id"));
		ourOrder.setOurOrderSheetId(result.getInt("our_order_sheet_id"));
		ourOrder.setMemberId(result.getString("memberId"));
		ourOrder.setStatus(OurOrderStatus.valueOf(result.getString("status")));
		return ourOrder;
	}
}
