/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Cleanup;
import lombok.val;
import tk.c4se.halt.ih31.nimunimu.config.DBConnector;
import tk.c4se.halt.ih31.nimunimu.dto.GoodsCategory;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class GoodsCategoryRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	protected final int perPage = 20;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public GoodsCategory find(int id) throws DBAccessException {
		if (id == 0) {
			return null;
		}
		val sql = "select * from goods_category where id = ?";
		GoodsCategory goodsCategory = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				goodsCategory = new GoodsCategory();
				setProperties(goodsCategory, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException();
		}
		return goodsCategory;
	}

	/**
	 * 
	 * @param idStr
	 * @return
	 * @throws DBAccessException
	 */
	public GoodsCategory find(String idStr) throws DBAccessException {
		@val
		int id;
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
	public List<GoodsCategory> all(int page) throws DBAccessException {
		val sql = "select * from goods_category where rownum between ? and ?";
		List<GoodsCategory> goodsCategories = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				GoodsCategory goodsCategory = new GoodsCategory();
				setProperties(goodsCategory, result);
				goodsCategories.add(goodsCategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException();
		}
		return goodsCategories;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<GoodsCategory> all() throws DBAccessException {
		return all(1);
	}

	public void insert(GoodsCategory goodsCategory) throws DBAccessException {
		val sql = "insert into goods_category(id, name) values (goods_category_pk_seq.nextval, ?)";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, goodsCategory.getName());
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					throw new DBAccessException(e1.getMessage());
				}
			}
			throw new DBAccessException(e.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DBAccessException(e.getMessage());
				}
			}
		}
	}

	public void update(GoodsCategory goodsCategory) throws DBAccessException {
		val sql = "update goods_category set name = ? where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, goodsCategory.getName());
			statement.setInt(2, goodsCategory.getId());
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					throw new DBAccessException();
				}
			}
			throw new DBAccessException();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DBAccessException();
				}
			}
		}
	}

	public void delete(GoodsCategory goodsCategory) throws DBAccessException {
		val sql = "delete from goods_category where id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, goodsCategory.getId());
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					throw new DBAccessException();
				}
			}
			throw new DBAccessException();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DBAccessException();
				}
			}
		}
	}

	private void setProperties(GoodsCategory goodsCategory, ResultSet result)
			throws SQLException {
		goodsCategory.setId(result.getInt("id"));
		goodsCategory.setName(result.getString("name"));
	}
}
