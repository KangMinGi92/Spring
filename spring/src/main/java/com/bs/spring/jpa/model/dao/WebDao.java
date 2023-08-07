package com.bs.spring.jpa.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bs.spring.jpa.entity.WebMember;

public interface WebDao {

	List<WebMember> selectMemberAll(EntityManager em);
	
	List<WebMember> selectMemberByName(EntityManager em, String name);
}
