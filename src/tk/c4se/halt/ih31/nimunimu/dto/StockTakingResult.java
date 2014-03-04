/**
 *
 */
package tk.c4se.halt.ih31.nimunimu.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author ne_Sachirou
 *
 */
@Data
public class StockTakingResult implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date stockTakingDate;
	private String place;
	private int goodsId;
	private Goods goods;
	private int expectedGoodsNumber;
	private int actualGoodsNumber;
}
