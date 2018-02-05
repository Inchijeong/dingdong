package com.dd.domain;

import java.util.Date;

import lombok.Data;

@Data
public class VisitorFile {
	private Integer visitFileNo;
	private String visitFileName;
	private String visitFilePath;
	private Long visitFileSize;
	private char visitFileType;
	private Date visitFileDate;
	private Integer userNo;
}