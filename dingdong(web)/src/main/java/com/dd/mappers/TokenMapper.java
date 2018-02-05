package com.dd.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dd.domain.Device;
import com.dd.domain.DingUser;

public interface TokenMapper extends CRUDMapper<DingUser, Long> {
	
	@Select("select * from tb_noti_device as n inner join tb_device_category as d on n.cate_code = d.cate_code where user_no=#{user_no}")
	public List<Device> getlist( @Param(value="user_no") Integer user_no);
}
