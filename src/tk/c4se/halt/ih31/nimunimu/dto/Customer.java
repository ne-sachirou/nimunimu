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
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String zipcode;
	private String address;
	private String tel;
	private String fax;
	private String person;
	private int billingCutoffDate;
	private int creditLimit;
}
