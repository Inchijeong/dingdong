package com.dd.domain;

import lombok.Data;

@Data
public class ResVoice {
	private Integer resFileNo;
	private String resFileTitle;
	private String resFileName;
	private String resFilePath;
	private long resFileSize;
	private int userNo;
	private String bellToken;
}