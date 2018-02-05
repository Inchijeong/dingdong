package com.dd.domain;

import java.util.Date;

import lombok.Data;

@Data
public class VisitorFiles {
	private Integer visitFileNo;
	private String visitFileName;
	private String visitVoiceName;
	private Date visitFileDate;
	private char visitFileType;
	private Integer userNo;
}