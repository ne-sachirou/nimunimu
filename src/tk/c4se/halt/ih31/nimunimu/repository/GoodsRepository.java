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
import tk.c4se.halt.ih31.nimunimu.dto.Goods;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class GoodsRepository extends RdbRepository<Goods> {
	private static final long serialVersionUID = 1L;

	public GoodsRepository() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public Goods find(int id) throws DBAccessException {
		if (id == 0) {
			return null;
		}
		val sql = "select * from goods where id = ?";
		Goods goods = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				goods = new Goods();
				setProperties(goods, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return goods;
	}

	/**
	 * 
	 * @param idStr
	 * @return
	 * @throws DBAccessException
	 */
	public Goods find(String idStr) throws DBAccessException {
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
	public List<Goods> all(int page) throws DBAccessException {
		val sql = "select * from goods where rownum between ? and ?";
		List<Goods> goods = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				Goods goodsItem = new Goods();
				setProperties(goodsItem, result);
				goods.add(goodsItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return goods;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<Goods> all() throws DBAccessException {
		return all(1);
	}

	public void insert(Goods goods) throws DBAccessException {
		val sql = "insert into goods(id, name, goods_category_id, supplier_id, price) values (goods_category_pk_seq.nextval, ?, ?, ?, ?)";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, goods.getName());
			statement.setInt(2, goods.getGoodsCategoryId());
			statement.setInt(3, goods.getSupplierId());
			statement.setInt(4, goods.getPrice());
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

	public void update(Goods goods) throws DBAccessException {
		val sql = "update goods set name = ?, goods_category_id = ?, supplier_id = ?, price = ? where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, goods.getName());
			statement.setInt(2, goods.getGoodsCategoryId());
			statement.setInt(3, goods.getSupplierId());
			statement.setInt(4, goods.getPrice());
			statement.setInt(5, goods.getId());
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

	public void delete(Goods goods) throws DBAccessException {
		val sql = "delete from goods where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, goods.getId());
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
	protected Goods setProperties(Goods goods, ResultSet result)
			throws SQLException {
		goods.setId(result.getInt("id"));
		goods.setName(result.getString("name"));
		goods.setGoodsCategoryId(result.getInt("goods_category_id"));
		goods.setSupplierId(result.getInt("supplier_id"));
		goods.setPrice(result.getInt("price"));
		return goods;
	}
}
