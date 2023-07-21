package com.bs.helloboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.helloboot.dto.MemberDto;

public interface MemberDao {

	List<MemberDto> selectMemberAll(SqlSession session);
	
	int insertMember(SqlSession session, MemberDto m);
	
	MemberDto selectMemberById(String userId);
	
	List<MemberDto> selectMemberByName(Map<String,Object> param);
}
