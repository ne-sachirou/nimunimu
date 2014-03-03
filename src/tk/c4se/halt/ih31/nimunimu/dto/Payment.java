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
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int supplierId;
	private Supplier supplier;
	private String memberId;
	private Member member;
	private PaymentStatus status;
}
