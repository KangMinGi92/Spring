package com.bs.spring.jpa.model.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Service;

import com.bs.spring.jpa.model.dao.JpaDao;

@Service
public class JpaServiceImpl implements JpaService {
	
	private EntityManager em;
	private JpaDao dao;
	
	public JpaServiceImpl(EntityManager em, JpaDao dao) {
		this.em=em;
		this.dao=dao;
	}
	
	@Override
	public void basictest() {
		EntityTransaction et=em.getTransaction();
		et.begin(); //트랜잭션 시작~~
			dao.basictest(em);
		et.commit(); // 트랜잭션이 종료 된 이후 flush() 자동으로 실행함. flush 란 영속성컨텍스트에 있는 DB에 반영하는 기능 SQL저장소에 있는 SQL문이 실행됨.
		
//		et.rollback();
	}

}
