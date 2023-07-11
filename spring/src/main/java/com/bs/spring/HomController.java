package com.bs.spring;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.bs.Test2;
import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Employee;
import com.bs.spring.beantest.FunctionalTest;
import com.bs.spring.include.TargetComponent;

@Controller
public class HomController {
	
	//springbean으로 등록된 객체는 필드로 가져와 사용할 수 있음
//	@Autowired
//	private Animal a;
//	@Autowired
//	private Animal b;
	
//  중복된 Animal을 불러올때는 id값으로 매칭해줘야한다.
//	@Autowired
//	private Animal bbo;
//	@Autowired
//	private Animal dog;
	
	//중복된 타입이 있는 경우 @Qualifier어노테이션을 이용해서 특정 bean을 선택 할 수 있음
	@Autowired
	@Qualifier("bbo")
	private Animal a;
	@Autowired
	@Qualifier("dog")
	private Animal b;
	
	//springBean에 등록되지 않은 객체를 Autowired하면 어떻게 될까? -> 에러발생
	//Autowired(옵션중에 required=false를 주면 beans가 있으면 넣고 없으면 에러띄우지 않고 넘어가는 옵션 default는 true)
	@Autowired(required=false)
	private Employee emp;
	
	@Autowired
	private Employee emp2;
	
	//java로 등록한 bean가져오기
	@Autowired
	@Qualifier("ani")
	private Animal c;
	
	@Autowired
	@Qualifier("sol")
	private Employee sol;
	
	@Autowired
	List<Animal> animals;
	
	@Autowired
	private TargetComponent tc;
	
	//@어노테이션으로 bean등록
	@Autowired
	private FunctionalTest ft;
	
	@Autowired
	private Test test;
	
//	basepackage 외부에 있는 @Component을 선언해도 bean으로 등록할 수 없다. basepackage를 밖에 있기때문에 
//	@Autowired
//	private Test2 t;
	
	@RequestMapping("/test")
	public String home() {
		System.out.println(a);
		System.out.println(b);
		System.out.println(emp);
		System.out.println(emp2);
		System.out.println(c);
		System.out.println(sol);
		animals.forEach(System.out::println);
		
		System.out.println(tc);
		System.out.println("FunctionalTest출력");
		System.out.println(ft);
		System.out.println(ft.getA());
		
		System.out.println(test);
		
		//spring에서 파일을 불러올 수 있는 Resource객체를 제공
		Resource resource=new ClassPathResource("mydata.properties");
		try {
			Properties prop=PropertiesLoaderUtils.loadProperties(resource);
			System.out.println(prop);
			resource=new FileSystemResource("C:\\Users\\GDJ\\git\\Spring\\spring\\src\\main\\resources\\test.txt");
			Files.lines(Paths.get(resource.getURI()),Charset.forName("UTF-8")).
			forEach(System.out::println);
			
			//주소값에 대한 정보를 불러올 수 있다.
			resource=new UrlResource("https://www.naver.com");
			InputStream is=resource.getInputStream();
			int d=0;
			StringBuffer sb=new StringBuffer();
			while((d=is.read())!=-1) {
				sb.append((char)d);
			}
			System.out.println(sb);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	
}
