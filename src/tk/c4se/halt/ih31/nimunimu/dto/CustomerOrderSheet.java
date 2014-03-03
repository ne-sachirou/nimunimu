/**
 * 
 */
package tk.c4se.halt.ih31.nimunimu.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author ne_Sachirou
 * 
 */
@Data
public class CustomerOrderSheet implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int amount;
	private int tax;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
}
