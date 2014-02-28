/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.repository;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.val;

import org.apache.commons.lang3.StringUtils;

import tk.c4se.halt.ih31.nimunimu.model.RdbScheme;

/**
 * @author ne_Sachirou
 * @param <M>
 * 
 */
public abstract class RdbRepository<M> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	protected final int perPage = 20;

	/**
	 * 
	 */
	@Getter
	@Setter
	protected RdbScheme scheme;

	/**
	 * 
	 * @param connection
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement findStatement(Connection connection,
			Map<String, Object> params) throws SQLException {
		List<String> conditions = new ArrayList<>();
		for (val pk : getScheme().getPrimaryKeys()) {
			conditions.add(pk + " = ?");
		}
		String sql = "select * from " + getScheme().getName() + " where "
				+ StringUtils.join(conditions, "and");
		PreparedStatement statement = connection.prepareStatement(sql);
		for (val pk : getScheme().getPrimaryKeys()) {
			val column = getScheme().getColumns().get(pk);
			val type = column.getType();
			@SuppressWarnings("rawtypes")
			Class paramType;
			if (type.equals("int")) {
				paramType = int.class;
			} else if (type.equals("string")) {
				paramType = String.class;
			} else {
				throw new SQLException("Unknown column type: " + type);
			}
			try {
				val method = statement.getClass().getMethod(
						"set" + StringUtils.capitalize(type),
						new Class[] { paramType });
				method.invoke(statement, paramType.cast(params.get(pk)));
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
				throw new SQLException(e);
			}
		}
		return statement;
	}

	/**
	 * 
	 * @param obj
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	protected M setProperties(M obj, ResultSet result) throws SQLException {
		for (val column : getScheme().getColumns().entrySet()) {
			val name = column.getKey();
			val type = column.getValue().getType();
			@SuppressWarnings("rawtypes")
			Class paramType;
			if (type.equals("int")) {
				paramType = int.class;
			} else if (type.equals("string")) {
				paramType = String.class;
			} else {
				throw new SQLException("Unknown column type: " + type);
			}
			try {
				val setMethod = obj.getClass().getMethod(
						"set" + StringUtils.capitalize(type),
						new Class[] { paramType });
				val getMethod = result.getClass().getMethod(
						"get" + StringUtils.capitalize(type),
						new Class[] { String.class });
				setMethod.invoke(obj, getMethod.invoke(result, name));
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
				throw new SQLException(e);
			}
		}
		return obj;
	}
}
