package com.bs.helloboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.helloboot.dto.MemberDto;
import com.bs.helloboot.service.JpaService;

@RestController
@RequestMapping("/jpa/member")
public class JpaController {
	
	private JpaService service;
	
	public JpaController(JpaService service) {
		this.service=service;
	
	}
	
	@GetMapping("/{id}")
	public MemberDto selectByUserId(@PathVariable("id") String id) {
		return service.selectById(id);
	}
	
	@GetMapping("insertMember")
	public ResponseEntity<MemberDto> insertMember(){
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		MemberDto insertMember=MemberDto.builder()
				.userId("user01")
				.password(encoder.encode("1234"))
				.address("서울시 금천구")
				.age(19)
				.gender("M")
				.email("test@test.com")
				.hobby("운동,코딩")
				.name("추가")
				.phone("1231234")
				.build();
		service.insertMember(insertMember);
		return ResponseEntity.ok(insertMember);
	}
}
