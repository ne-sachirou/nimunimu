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
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	private String place;
	private String goodsId;
	private Goods goods;
	private int goodsNumber;
}
