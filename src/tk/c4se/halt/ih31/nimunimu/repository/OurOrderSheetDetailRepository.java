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
import tk.c4se.halt.ih31.nimunimu.dto.OurOrderSheetDetail;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class OurOrderSheetDetailRepository extends
		RdbRepository<OurOrderSheetDetail> {
	private static final long serialVersionUID = 1L;

	public OurOrderSheetDetailRepository() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public OurOrderSheetDetail find(int id) throws DBAccessException {
		if (id == 0) {
			return null;
		}
		val sql = "select * from our_order_sheet_detail where id = ?";
		OurOrderSheetDetail detail = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				detail = new OurOrderSheetDetail();
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
	public OurOrderSheetDetail find(String idStr) throws DBAccessException {
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
	public List<OurOrderSheetDetail> all(int page) throws DBAccessException {
		val sql = "select * from our_order_sheet_detail where rownum between ? and ?";
		List<OurOrderSheetDetail> details = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				OurOrderSheetDetail detail = new OurOrderSheetDetail();
				setProperties(detail, result);
				details.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return details;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<OurOrderSheetDetail> all() throws DBAccessException {
		val sql = "select * from our_order_sheet_detail ?";
		List<OurOrderSheetDetail> details = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				OurOrderSheetDetail detail = new OurOrderSheetDetail();
				setProperties(detail, result);
				details.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return details;
	}

	public void insert(OurOrderSheetDetail detail) throws DBAccessException {
		val sql = "insert into our_order_sheet_detail(id, our_order_sheet_id, goods_id, price, goods_number) values (our_order_sheet_detail_pk_seq.nextval, ?, ?, ?, ?)";
		val sql2 = "select our_order_sheet_detail_pk_seq.currval from dual";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, detail.getOurOrderSheetId());
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

	public void update(OurOrderSheetDetail detail) throws DBAccessException {
		val sql = "update our_order_sheet_detail set our_order_sheet_id = ?, goods_id = ?, price = ?, goods_number = ? where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, detail.getOurOrderSheetId());
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

	public void delete(OurOrderSheetDetail detail) throws DBAccessException {
		val sql = "delete from our_order_sheet_detail where id = ?";
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
	protected OurOrderSheetDetail setProperties(OurOrderSheetDetail detail,
			ResultSet result) throws SQLException {
		detail.setId(result.getInt("id"));
		detail.setOurOrderSheetId(result.getInt("our_order_sheet_id"));
		detail.setGoodsId(result.getString("goods_id"));
		detail.setPrice(result.getInt("price"));
		detail.setGoodsNumber(result.getInt("goods_number"));
		return detail;
	}
}
