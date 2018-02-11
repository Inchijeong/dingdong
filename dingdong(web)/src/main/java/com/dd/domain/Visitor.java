/**
 * @author Inchijeong
 */
package com.dd.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Visitor {
	private Integer userNo;
	private String gender;
	private Integer age;
	private Integer generation;
	private Date visitDate;
}
