package com.dd.domain;

import lombok.Data;

@Data
public class Device {
	private String noti_token, noti_name, cate_name;
	private int user_no, cate_code, noti_receive;
}