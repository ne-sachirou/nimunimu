/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.dto;

/**
 * 
 * @author ne_Sachirou
 */
public enum CustomerOrderStatus {
	/** 未見積 */
	YET_ESTIMATE,
	/** 見積済み */
	ESTIMATED,
	/** 未受注 */
	YET_ACCEPT,
	/** 未納品 */
	YET_DELIVER,
	/** 納品済み */
	DELIVERED,
}