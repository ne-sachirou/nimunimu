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
public class BillingDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private int billingId;
	private Billing billing;
	private int customerOrderid;
	private CustomerOrder customerOrder;
}
