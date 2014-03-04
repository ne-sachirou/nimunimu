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
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String memberId;
	private String message;
	private Date createdAt;
	private Date updatedAt;
}