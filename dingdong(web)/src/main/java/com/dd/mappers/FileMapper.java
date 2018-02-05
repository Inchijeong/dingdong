package com.dd.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dd.domain.VisitorFiles;

public interface FileMapper extends CRUDMapper<VisitorFiles, Integer> {
	
	@Select("select count(*) from tb_visitor_files where user_no=#{userNo}")
	public int getlistCount(@Param(value="userNo") Integer userNo);
	
//	@Select("select * from tb_visitor_files where user_no=#{userNo} and visit_file_no >= #{listCount}-8 and visit_file_no <= #{listCount} order by visit_file_no desc")
	public List<VisitorFiles> getlist( @Param(value="userNo") Integer userNo, @Param(value="listCount") Integer listCount);
	
//	@Select("select * from tb_visitor_files where user_no = 1 and visit_file_no >= #{start} and visit_file_no <= #{end} order by visit_file_no desc")
	public List<VisitorFiles> getlistMore( @Param(value="userNo") Integer userNo, @Param(value="start") Integer start,  @Param(value="end")Integer end);

}
