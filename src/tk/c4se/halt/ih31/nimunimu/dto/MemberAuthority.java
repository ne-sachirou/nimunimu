/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.dto;

/**
 * 社員account account権限
 * 
 * @author ne_Sachirou
 */
public enum MemberAuthority {
	/** system管理者 (社員account等の管理) */
	ADMIN,
	/** 営業 (一般営業社員) */
	SALES,
	/** 営業管理者 (管理職) */
	SALES_MANAGER,
	/** 在庫 (picking, 棚卸) 担当 */
	STORE,
	/** 在庫管理者 */
	STORE_MANAGER,
	/** 経理 */
	ACCOUNTING,
}