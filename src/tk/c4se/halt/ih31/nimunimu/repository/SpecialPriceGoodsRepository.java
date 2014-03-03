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
import tk.c4se.halt.ih31.nimunimu.dto.SpecialPriceGoods;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class SpecialPriceGoodsRepository extends
		RdbRepository<SpecialPriceGoods> {
	private static final long serialVersionUID = 1L;

	public SpecialPriceGoodsRepository() {
		super();
	}

	/**
	 * 
	 * @param goodsId
	 * @param customerId
	 * @return
	 * @throws DBAccessException
	 */
	public SpecialPriceGoods find(String goodsId, int customerId)
			throws DBAccessException {
		val sql = "select * from special_price_goods where goods_id = ? and customer_id = ?";
		SpecialPriceGoods specialPriceGoods = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, goodsId);
			statement.setInt(2, customerId);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				specialPriceGoods = new SpecialPriceGoods();
				setProperties(specialPriceGoods, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return specialPriceGoods;
	}

	/**
	 * 
	 * @param page
	 * @return
	 * @throws DBAccessException
	 */
	public List<SpecialPriceGoods> all(int page) throws DBAccessException {
		val sql = "select * from special_price_goods where rownum between ? and ?";
		List<SpecialPriceGoods> specialPriceGoodsList = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				SpecialPriceGoods specialPriceGoods = new SpecialPriceGoods();
				setProperties(specialPriceGoods, result);
				specialPriceGoodsList.add(specialPriceGoods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return specialPriceGoodsList;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<SpecialPriceGoods> all() throws DBAccessException {
		return all(1);
	}

	public void insert(SpecialPriceGoods specialPriceGoods)
			throws DBAccessException {
		val sql = "insert into special_price_goods(goods_id, customer_id, price) values (?, ?, ?)";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, specialPriceGoods.getGoodsId());
			statement.setInt(2, specialPriceGoods.getCustomerId());
			statement.setInt(3, specialPriceGoods.getPrice());
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

	public void update(SpecialPriceGoods specialPriceGoods)
			throws DBAccessException {
		val sql = "update special_price_goods set price = ? where goods_id = ? and customer_id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, specialPriceGoods.getPrice());
			statement.setString(2, specialPriceGoods.getGoodsId());
			statement.setInt(3, specialPriceGoods.getCustomerId());
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

	public void delete(SpecialPriceGoods apecialPriceGoods)
			throws DBAccessException {
		val sql = "delete from special_price_goods where goods_id = ? and customer_id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, apecialPriceGoods.getGoodsId());
			statement.setInt(2, apecialPriceGoods.getCustomerId());
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
	protected SpecialPriceGoods setProperties(
			SpecialPriceGoods specialPriceGoods, ResultSet result)
			throws SQLException {
		specialPriceGoods.setGoodsId(result.getString("goods_id"));
		specialPriceGoods.setCustomerId(result.getInt("customer_id"));
		specialPriceGoods.setPrice(result.getInt("price"));
		return specialPriceGoods;
	}
}
