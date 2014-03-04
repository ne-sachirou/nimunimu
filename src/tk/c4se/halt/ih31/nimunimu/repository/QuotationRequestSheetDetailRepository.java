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
import tk.c4se.halt.ih31.nimunimu.dto.QuotationRequestSheetDetail;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 *
 */
public class QuotationRequestSheetDetailRepository extends
		RdbRepository<QuotationRequestSheetDetail> {
	private static final long serialVersionUID = 1L;

	public QuotationRequestSheetDetailRepository() {
		super();
	}

	/**
	 *
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public QuotationRequestSheetDetail find(int id) throws DBAccessException {
		if (id == 0) {
			return null;
		}
		val sql = "select * from quotation_request_sheet_detail where id = ?";
		QuotationRequestSheetDetail detail = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				detail = new QuotationRequestSheetDetail();
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
	public QuotationRequestSheetDetail find(String idStr)
			throws DBAccessException {
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
	public List<QuotationRequestSheetDetail> all() throws DBAccessException {
		val sql = "select * from quotation_request_sheet_details";
		List<QuotationRequestSheetDetail> details = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				QuotationRequestSheetDetail detail = new QuotationRequestSheetDetail();
				setProperties(detail, result);
				details.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return details;
	}

	public List<QuotationRequestSheetDetail> allByQuotationRequestSheetId(
			int QuotationRequestSheetId) throws DBAccessException {
		val sql = "select * from quotation_request_sheet_detail where quotation_request_sheet_id = ?";
		List<QuotationRequestSheetDetail> details = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, QuotationRequestSheetId);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				QuotationRequestSheetDetail detail = new QuotationRequestSheetDetail();
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
	 * @param QuotationRequestSheetId
	 * @return
	 * @throws DBAccessException
	 */
	public List<QuotationRequestSheetDetail>
		allByQuotationRequestSheetId(String QuotationRequestSheetId)
			throws DBAccessException {
		final int id;
		try {
			id = Integer.parseInt(QuotationRequestSheetId);
		} catch (NumberFormatException e) {
			return null;
		}
		return allByQuotationRequestSheetId(id);
	}

	public void insert(QuotationRequestSheetDetail detail)
			throws DBAccessException {
		val sql = "insert into quotation_request_sheet_detail(id, quotation_request_sheet_id, goods_id, price, goods_number) values (sequence quotation_rqst_sht_dtl_pk_s.nextval, ?, ?, ?, ?)";
		val sql2 = "select sequence quotation_rqst_sht_dtl_pk_s.currval from dual";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, detail.getQuotationRequestSheetId());
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

	public void update(QuotationRequestSheetDetail detail)
			throws DBAccessException {
		val sql = "update quotation_request_sheet_detail set quotation_request_sheet_id = ?, goods_id = ?, price = ?, goods_number = ? where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, detail.getQuotationRequestSheetId());
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

	public void delete(QuotationRequestSheetDetail detail)
			throws DBAccessException {
		val sql = "delete from quotation_request_sheet_detail where id = ?";
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
	protected QuotationRequestSheetDetail setProperties(
			QuotationRequestSheetDetail detail, ResultSet result)
			throws SQLException {
		detail.setId(result.getInt("id"));
		detail.setQuotationRequestSheetId(result
				.getInt("quotation_request_sheet_id"));
		detail.setGoodsId(result.getString("goods_id"));
		detail.setPrice(result.getInt("price"));
		detail.setGoodsNumber(result.getInt("goods_number"));
		return detail;
	}
}
