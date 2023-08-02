package com.bs.spring.jpa.common;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {
	
	//jpa로 DB에 접속하기 위해서
	//EntityManager객체가 필요함.
	//EntityManager객체는 EntityManagerFactory클래스의
	//createEntityManager()메소드를 이용해서 가져올 수 있다.
	//EntityManagerFactory클래스를 가져오기 위해서는
	//Persistence클래스의 static메소드인 createEntityManagerFactory()메소드를 이용한다.

	
	//생성할 객체를 bean으로 등록해서 사용하자
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("bstest");
		return factory;
	}
	
//	@Bean
//	public EntityManager entityManger() {
//		return entityManagerFactory().createEntityManager();
//	}
}
