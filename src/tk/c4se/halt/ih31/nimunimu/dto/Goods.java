/**
 *
 */
package tk.c4se.halt.ih31.nimunimu.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author ne_Sachirou
 *
 */
@Data
public class Goods implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private int goodsCategoryId;
	private GoodsCategory goodsCategory = null;
	private int supplierId;
	private Supplier supplier = null;
	private int price;
}
