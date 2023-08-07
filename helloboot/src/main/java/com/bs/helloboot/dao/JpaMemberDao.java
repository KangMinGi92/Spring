package com.bs.helloboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bs.helloboot.dto.MemberDto;

@Repository
public interface JpaMemberDao extends JpaRepository<MemberDto,String>{
	
}
