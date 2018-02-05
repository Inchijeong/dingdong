package com.dd.domain;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	private Integer userNo;
	private String email;
	private String password;
	private String userName;
	private String phone;
	private Date regdate;
	private Date updatedate;
	private String bellToken;
	private Integer enabled;
}
