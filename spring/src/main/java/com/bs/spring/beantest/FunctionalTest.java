package com.bs.spring.beantest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//pojo클래스를 생성하고 이거를 선언부에서 bean으로 등록할 수 있다.
//@Component, @Controller, @Service, @Repository
//어노테이션을 이용해서 spring bean으로 등록 가능하다.
//@Component : 기본 spring bean으로 등록할 때 사용
//@Controller, @Service, @Repository : mvc패턴에 의해 역할 지정된 클래스를 등록할 때 사용


//@Data
@Component
public class FunctionalTest {
	
	private String name="test";
	
	//@Autowired
	private Animal a;
	
	
	//생성자를 이용한 DI(의존성주입)
//	public FunctionalTest(@Qualifier("dog") Animal a) {
//		this.a=a;
//	}
	
	//setter를 이용한 DI
	@Autowired
	public void setA(@Qualifier("dog") Animal a) {
		this.a=a;
	}
	public Animal getA() {
		return this.a;
	}
}
