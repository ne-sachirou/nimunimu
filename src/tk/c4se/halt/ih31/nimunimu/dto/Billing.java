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
public class Billing implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int customerId;
	private Customer customer;
	private String memberId;
	private Member member;
	private BillingStatus status;
}
