package com.dd.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dd.domain.Device;
import com.dd.dto.Criteria;

public interface DeviceMapper extends DeviceCRUDMapper<Device, Long> {

	@Select("select * from tb_noti_device as n inner join tb_device_category as d on n.cate_code = d.cate_code where user_no=#{user_no}")
	public List<Device> getlist( @Param(value="user_no") Integer user_no);

	@Select("select noti_token from tb_noti_device where user_no = #{user_no} and noti_receive = 1")
	public List<String> getNotiTokenList(Integer user_no);
	
	
	@Select("select bell_token from tb_user where user_no = #{user_no}")
	public String getBellToken(Integer user_no);
	
	@Update("update tb_user set bell_token = null where user_no = #{user_no}")
	public void bellRemove(Integer user_no);
}
