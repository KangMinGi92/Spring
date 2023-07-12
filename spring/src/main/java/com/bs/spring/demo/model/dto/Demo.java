package com.bs.spring.demo.model.dto;

import java.sql.Date;
//java.util.Date로 임포트하면 프론트단에서 date가 넘어오면 처리 못해서 sqlDate로 처리해야함
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Demo {
	private Long devNo;
	private String devName;
	private int devAge;
	private String devEmail;
	private String devGender;
	private String[] devLang;
	//private Date birthDay;
	
}
