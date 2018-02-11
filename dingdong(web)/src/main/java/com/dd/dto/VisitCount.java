/**
 * @author Inchijeong
 */
package com.dd.dto;

import java.util.Date;

import lombok.Data;

@Data
public class VisitCount {
	private Date roundDate;
	private Integer population;
}
