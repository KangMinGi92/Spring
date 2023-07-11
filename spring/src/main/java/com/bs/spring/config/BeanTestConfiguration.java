package com.bs.spring.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Department;
import com.bs.spring.beantest.Employee;

//클래스 방식으로 bean등록해서 사용하기
// pojo클래스를 configuration으로 사용할 수 있음 -> @Configuration어노테이션 이용

@Configuration
@ComponentScan(basePackages="com.bs.spring",
		includeFilters= {@ComponentScan.Filter(type= FilterType.REGEX,pattern= {"com.bs.spring.include.*"})},
		//어노테이션 표시가 없더라도 해당이 되면 bean으로 등록함
		excludeFilters=	{}
		//어노테이션 표시가 있더라도 해당이 되면 bean에서 등록하지 않음
		)
@Import(value = {})//다른configuration을 가져와 처리하는 것
public class BeanTestConfiguration {
	//springbeanconfiguration.xml 과 동일한 기능
	
	//spring에서 사용할 bean을 자바코드로 등록 할 수 있다.
	//@Bean어노테이션을 이용
	//메소드 선언을 통해 등록함
	//beans:bean id=ani가 되고 나머지 세팅값이 property name="name", value="킥킥"
	@Bean
	@Order(1)//bean우선순위를 설정 할 수 있다.
	public Animal ani() {
		return Animal.builder().name("킥킥").age(5).height(80).build();
	}
	
	@Bean
	//등록된 bean에 특정 id값 부여하기
	@Qualifier("sol")
	//@Autowired 스프링 4버전 이후부터는 안써줘도 자동으로 맵핑해줌
	public Employee getEmployee(@Qualifier("sal")Department d) {
		return Employee.builder().name("최솔").age(27).address("경기도 안양시").salary(200).dept(d).build();
	}
	
	@Bean
	public Department sal() {
		return Department.builder().deptCode(2L).depTitle("영업부").deptLocation("서울").build();
	}
	
	@Bean
	public BasicDataSource getDataSource() {
		BasicDataSource source=new BasicDataSource();
		source.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		source.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		source.setUsername("spring");
		source.setPassword("spring");
		return source;
	}
	
//	@Bean
//	public Gson gson() {
//		return new Gson();
//	}
}
