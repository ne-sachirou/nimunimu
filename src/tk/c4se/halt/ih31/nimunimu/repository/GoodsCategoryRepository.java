/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.repository;

import java.io.Serializable;
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

	private void setProperties(GoodsCategory goodsCategory, ResultSet result)
			throws SQLException {
		goodsCategory.setId(result.getInt("id"));
		goodsCategory.setName(result.getString("name"));
	}
}
