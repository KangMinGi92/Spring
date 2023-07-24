package com.bs.spring.security.controller;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bs.spring.member.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;

public class SecurityLoginService implements UserDetailsService{
	
	private MemberDao dao;
	private SqlSessionTemplate session;
	
	
	@Autowired
	public SecurityLoginService(MemberDao dao, SqlSessionTemplate session) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member loginMember=dao.selectMemberById(session, Map.of("userId",username));
		
		return loginMember;
	}
	
	
	
}
