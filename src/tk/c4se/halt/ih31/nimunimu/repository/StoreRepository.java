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
import tk.c4se.halt.ih31.nimunimu.dto.Store;
import tk.c4se.halt.ih31.nimunimu.exception.DBAccessException;

/**
 * @author ne_Sachirou
 * 
 */
public class StoreRepository extends RdbRepository<Store> {
	private static final long serialVersionUID = 1L;

	public StoreRepository() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DBAccessException
	 */
	public Store find(String place, String goodsId) throws DBAccessException {
		val sql = "select * from store where place = ? and goods_id = ?";
		Store store = null;
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, place);
			statement.setString(2, goodsId);
			@Cleanup
			val result = statement.executeQuery();
			if (result.next()) {
				store = new Store();
				setProperties(store, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return store;
	}

	/**
	 * 
	 * @param page
	 * @return
	 * @throws DBAccessException
	 */
	public List<Store> all(int page) throws DBAccessException {
		val sql = "select * from store where rownum between ? and ?";
		List<Store> stores = new ArrayList<>();
		try (val connection = DBConnector.getConnection()) {
			@Cleanup
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, perPage * (page - 1) + 1);
			statement.setInt(2, perPage * page);
			@Cleanup
			val result = statement.executeQuery();
			while (result.next()) {
				Store store = new Store();
				setProperties(store, result);
				stores.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBAccessException(e);
		}
		return stores;
	}

	/**
	 * 
	 * @return
	 * @throws DBAccessException
	 */
	public List<Store> all() throws DBAccessException {
		return all(1);
	}

	public void insert(Store store) throws DBAccessException {
		val sql = "insert into store(place, goods_id, goods_number) values (?, ?, ?)";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, store.getPlace());
			statement.setString(2, store.getGoodsId());
			statement.setInt(3, store.getGoodsNumber());
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

	public void update(Store store) throws DBAccessException {
		val sql = "update store set goods_number = ? where place = ? and goods_id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, store.getGoodsNumber());
			statement.setString(2, store.getPlace());
			statement.setString(3, store.getGoodsId());
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

	public void delete(Store store) throws DBAccessException {
		val sql = "delete from store where place = ? and goods_id = ?";
		Connection connection = null;
		try {
			connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, store.getPlace());
			statement.setString(2, store.getGoodsId());
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
	protected Store setProperties(Store store, ResultSet result)
			throws SQLException {
		store.setPlace(result.getString("place"));
		store.setGoodsId(result.getString("goods_id"));
		store.setGoodsNumber(result.getInt("goods_number"));
		return store;
	}
}
