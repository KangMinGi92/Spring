package com.bs.spring.ajax.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ajax")
@Slf4j
public class AjaxController {

	@Autowired
	private MemberService memberservice;
	
	@GetMapping("/basicTest.do")
	public void basic(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		Board b=Board.builder().boardTitle("냉무").boardContent("냉무").build();
		ObjectMapper mapper=new ObjectMapper();
//		res.setContentType("text/csv;charset=utf-8");
//		res.getWriter().write("test");
		res.setContentType("application/json;charset=utf-8");
		res.getWriter().write(mapper.writeValueAsString(b));
	}
	
	
	//리턴값에 반환할 객체를 선언
	//@ResponseBody -> json으로 변환할 수 있게 처리
	@GetMapping("/converter")
	@ResponseBody
	public Board convertTest() {
		return Board.builder().boardTitle("spring좋다!").boardContent("하하하하하").build();
	}
	
	@PostMapping("/idduplicate")
	@ResponseBody
	public Member idDuplicate(@RequestParam Map param) {
		log.info("{}",param);
		return memberservice.selectMemberById(param);
	}
	
	@GetMapping("/basic2")
	public String basic2() {
		return "demo/demo";
	}
	
	@GetMapping("/selectMemberAll")
	@ResponseBody
	public List<Member> selectMemberAll() {
		return memberservice.selectMemberAll();
	}
	
	//파라미터에서 받아올 데이터를 파싱해서 객체를 선언
	//@RequestBody -> json으로 변환할 수 있게 처리
	
	@PostMapping("/insertData.do")
	@ResponseBody
	public Member insertData(@RequestBody Member m) {
		log.info("{}",m);
		return m;
	}
	
}
