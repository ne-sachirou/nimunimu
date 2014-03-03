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
public class OurOrderSheetDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int ourOrderSheetId;
	private OurOrderSheet ourOrderSheet;
	private String goodsId;
	private Goods goods;
	private int price;
	private int goodsNumber;
}
