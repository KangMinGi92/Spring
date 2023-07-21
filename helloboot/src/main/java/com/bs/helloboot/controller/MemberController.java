package com.bs.helloboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.helloboot.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private MemberService service;
	
	public MemberController(MemberService service) {
		this.service=service;
	}
	
//MyWebMvcConfiguration에 등록해서 주석처리해도 /로 접근했을때 index.jsp로 매핑해준다.
//페이지 전환용 메소드를 따로 만들어서 설정하지말고 MyWebMvcConfiguration을 이용해서 처리해 줄 수 있다.	
	
//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}
	
	@GetMapping("/memberAll")
	public String selectMemberAll(Model m) {
		m.addAttribute("member",service.selectMemberAll());
		return "member/memberList";
	}
}
