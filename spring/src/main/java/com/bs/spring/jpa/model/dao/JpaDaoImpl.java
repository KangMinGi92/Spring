package com.bs.spring.jpa.model.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.entity.JpaMember;
@Repository
public class JpaDaoImpl implements JpaDao {

	@Override
	public void basictest(EntityManager em) {
		//em메소드 이용하기....
		//1. JpaMember 클래스 영속성 등록하기
		JpaMember m=JpaMember.builder()
					.memberId("20200120")
					.memberPwd("rhrhrh22")
					.age(27)
					.height(178.2)
					.build(); //->비영속 객체
		//영속화 처리하기
		em.persist(m); //매개변수로 전달된 객체가 영속성 컨텍스트에 저장이 되고, 
					   //영속성컨테스트에 새로 저장되면 INSERT SQL문을 자동으로 생성한다(실행하지 않음, 실행은 서비스에서 transaction 처리 이후 실행).
		System.out.println(m);
		
		JpaMember m2=JpaMember.builder()
				.memberId("김현빵")
				.memberPwd("빵빵으악")
				.age(26)
				.height(165.2)
				.build();
		em.persist(m2);
		
		//저장된 객체 불러오기 -> select문
		JpaMember selectM=em.find(JpaMember.class,1L);
		System.out.println(selectM);
	}

}
