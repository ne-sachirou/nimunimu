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
public class QuotationRequestSheetDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int quotationRequestSheetId;
	private QuotationRequestSheet quotationRequestSheet;
	private String goodsId;
	private Goods goods;
	private int price;
	private int goodsNumber;
}
