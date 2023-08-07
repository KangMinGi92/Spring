package com.bs.spring.jpa.model.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.entity.WebMember;
@Repository
public class WebDaoImpl implements WebDao {

	@Override
	public List<WebMember> selectMemberAll(EntityManager em) {
		WebMember member=em.find(WebMember.class,"admin");
		System.out.println(member);
		//jpa가 제공하는 메소드를 이용하면 pk값으로 한개의 row만 가져올 수 있음.
		//JPQL이라는 객체지향 SQL문을 제공함 -> insert문은 존재 하지 않음
		//SELECT 별칭.필드명,...|| 별칭(전체컬럼) 
		//FROM 엔티티명 별칭 [INNER||LEFT||RIGHT] JOIN [FETCH] 별칭.has a 관계에있는 엔티티 필드명
		//[WHERE, GROUP BY, HAVING, ORDER BY,]  
		
		String sql="""
				SELECT m
				FROM WebMember m
				""";//SELECT * FROM MEMBER

		//em.createQuery(sql);실행시 반환형 2가지
		//1. Query : 조회결과 타입이 지정되지 않았을때 사용(뭘로 지정해야할지 모를때도 이거사용)
		//2. TypedQuery<Type> : 조회결과 타입이 지정되었을때 사용
		TypedQuery<WebMember> tquery=em.createQuery(sql,WebMember.class); //불러올때부터 WebMember로 지정
		Query query=em.createQuery(sql); // 불러올때 타입이 정해지지 않음
		//생성된 query로 데이터가져오기
		// getResultList()메소드를 이용 : List로 row를 가져옴.
		// getSingleResult()메소드를 이용 : 한개 row만 반환하는 쿼리문 -> 다중 row면 Exception 발생
		// getResultStream()메소드를 이용 : row들을 stream으로 반환
		
		//페이징처리하기
		//query클래스에서 페이징처리하는 메소드를 제공함.
		//setFirstResult() -> 시작인덱스 0부터시작 -> cPage
		//setMaxResult() -> 조회할 갯수 -> numPerPage
		tquery.setFirstResult(0);
		tquery.setMaxResults(10);
		
		List<WebMember> result=tquery.getResultList();
		List result2=query.getResultList();
		
		System.out.println("tquery 실행결과");
		result.forEach(System.out::println);
		
		System.out.println("query 실행결과");
		result2.forEach(System.out::println);
		
		
		//컬럼선택해서 조회하기
		//프로젝션(select문 안에 특정 컬럼을 지칭하는 명칭) 사용하기
		sql="""
			SELECT m.userName,m.email,m.age
			FROM WebMember m	
			""";
		query=em.createQuery(sql);//결과는 Object[]생성해서 결과를 반환.
		System.out.println("컬럼 선택해서 가져오기");
		List<Object[]> result3=query.getResultList();
		result3.forEach(e->{
			System.out.println(e[0]+" "+e[1]+" "+e[2]);
		});
//		query.getResultStream().forEach(e->{
//			Object[] oarr=(Object[])e;
//			System.out.println(oarr[0]+" "+oarr[1]+" "+oarr[2]);
//		});
		
		sql="""
			SELECT AVG(m.age), sum(m.age), count(m)
			FROM WebMember m
			GROUP BY m.gender
			HAVING count(m)>50
			""";
		query=em.createQuery(sql);
//		Object[] group=(Object[])query.getSingleResult();
		result3=query.getResultList();
		System.out.println("그룹함수 이용");
		result3.forEach(e->{
			System.out.println(e[0]+" "+e[1]+" "+e[2]);
		});
//		System.out.println(Arrays.toString(group));
//		System.out.println(group);
		
		
		return result;
	}

	@Override
	public List<WebMember> selectMemberByName(EntityManager em, String name) {
		//매개변수를 받아서 처리하는 JPQL만들기
		String sql="""
				SELECT m
				FROM WebMember m
				WHERE m.userName LIKE '%'||:name||'%'
				""";
		TypedQuery<WebMember> tquery=em.createQuery(sql,WebMember.class);
		//setParameter()메소드로 매개변수 세팅
		tquery.setParameter("name",name);
		
		return tquery.getResultList();
	}
	
	

}
