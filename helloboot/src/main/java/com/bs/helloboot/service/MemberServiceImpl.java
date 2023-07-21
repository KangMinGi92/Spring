package com.bs.helloboot.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.helloboot.dao.MemberDao;
import com.bs.helloboot.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberDao dao;
	private SqlSession session;
	public MemberServiceImpl(SqlSession session,MemberDao dao) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
	public List<MemberDto> selectMemberAll() {
		return dao.selectMemberAll(session);
	}

	@Override
	public int insertMember(MemberDto m) {
		return dao.insertMember(session, m);
	}
	
	@Override
	public MemberDto selectMemberById(String userId) {
		return dao.selectMemberById(userId);
	}
	@Override
	public List<MemberDto> selectMemberByName(Map<String,Object> param){
		return dao.selectMemberByName(param);
	}

}
