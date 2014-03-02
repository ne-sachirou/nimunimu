/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.val;

/**
 * @author ne_Sachirou
 * 
 */
@Data
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String zipcode;
	private String address;
	private String tel;
	private String fax;
	private String person;
	@Getter
	private int billingCutoffDate;

	public void setBillingCutoffDate(int billingCutoffDate) {
		this.billingCutoffDate = billingCutoffDate;
	}

	public void setBillingCutoffDate(String billingCutoffDateStr) {
		@val
		int billingCutoffDate;
		try {
			billingCutoffDate = Integer.parseInt(billingCutoffDateStr);
		} catch (NumberFormatException e) {
			billingCutoffDate = 15;
		}
		this.billingCutoffDate = billingCutoffDate;
	}
}
