package com.dd.domain;

import java.util.Date;

import lombok.Data;

@Data
public class File {
	private String visit_file_name, visit_file_path;
	private int visit_file_size, visit_file_type, user_no, visit_file_no;
	private Date visit_file_date;
}
