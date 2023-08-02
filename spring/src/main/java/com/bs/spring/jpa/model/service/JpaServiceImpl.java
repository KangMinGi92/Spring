package com.bs.spring.jpa.model.service;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Service;

import com.bs.spring.jpa.model.dao.JpaDao;

@Service
public class JpaServiceImpl implements JpaService {
	
	private EntityManagerFactory factory;
	//private EntityManager em;
	private JpaDao dao;
	
	public JpaServiceImpl(EntityManagerFactory factory, JpaDao dao) {
		this.factory=factory;
		this.dao=dao;
	}
	
	@Override
	public void basictest() {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin(); //트랜잭션 시작~~
			dao.basictest(em);
		et.commit(); // 트랜잭션이 종료 된 이후(즉 commit()실행직 후)
					//flush() 자동으로 실행함. flush 란 영속성컨텍스트에 있는 DB에 반영하는 기능 SQL저장소에 있는 SQL문이 실행됨.
		
//		et.rollback();
		em.close();
	}
	
	@Override
	public void manytoone() {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
			dao.manytoone(em);
		et.commit();
		//em.clear();
		dao.boardById(em, 1);
		em.close();
	}

	@Override
	public void insertStudent() {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
			dao.insertStudent(em);
		et.commit();
		
		em.clear();
		dao.selectStudentById(em,1);
		em.close();
		
	}

	@Override
	public void deleteStudent(long no) {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
			dao.deleteStudent(em,no);
		et.commit();
		em.close();
	}

	@Override
	public void updateStudent(Map<String, Object> param) {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
			dao.updateStudent(em,param);
		et.commit();
		em.close();
		
	}

	@Override
	public void insertClub() {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
			dao.insertClub(em);
		et.commit();
		em.close();
		
	}
	
	
	
	
	

}
