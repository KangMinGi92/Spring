package com.bs.spring.jpa.model.service;

import java.util.List;

import com.bs.spring.jpa.entity.WebMember;

public interface WebService {
	List<WebMember> selectMemberAll();
	List<WebMember> selectMemberByName(String name);
}
