package com.bs.helloboot.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.bs.helloboot.dto.MemberDto;

@Mapper
public interface MemberMapper {
	
	@Select("SELECT * FROM MEMBER")
	List<MemberDto> selectMemberAll();
	
	@Select(value="SELECT * FROM MEMBER WHERE USERID=#{id}")
	MemberDto selectMemberById(String id);
	
	
	@SelectProvider(type=MemberSelectBuilder.class, method="selectMemberByName")
	List<MemberDto> selectMemberByName(Map<String,Object> param);
}
