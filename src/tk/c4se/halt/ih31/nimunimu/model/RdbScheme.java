/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ne_Sachirou
 * 
 */
public class RdbScheme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Data
	public class RdbColumn {
		private String name;
		private String type;
	}

	@Getter
	@Setter
	private String name;
	@Getter
	private Map<String, RdbColumn> columns = new HashMap<>();
	@Getter
	private List<String> primaryKeys = new ArrayList<>();

	/**
	 * 
	 * @param name
	 * @param type
	 */
	public void addColumn(String name, String type) {
		RdbColumn column = new RdbColumn();
		column.setName(name);
		column.setType(type);
		columns.put(name, column);
	}

	/**
	 * 
	 * @param columnName
	 */
	public void addPrimaryKey(String columnName) {
		primaryKeys.add(columnName);
	}
}
