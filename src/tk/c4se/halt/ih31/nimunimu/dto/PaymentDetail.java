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
public class PaymentDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private int paymentId;
	private Payment payment;
	private int ourOrderId;
	private OurOrder ourOrder;
}
