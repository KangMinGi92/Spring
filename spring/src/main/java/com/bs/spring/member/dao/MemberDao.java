package com.bs.spring.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.member.model.dto.Member;

public interface MemberDao {
	
	int insertMember(SqlSessionTemplate session, Member m);
	
	Member selectMemberById(SqlSessionTemplate session, Map param);
	
	int updateMember(SqlSessionTemplate session, Member m);
}
