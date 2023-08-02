package com.bs.spring.jpa.common.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.jpa.model.service.JpaService;

@Controller
@RequestMapping("/jpa")
public class JpaController {
	
	private JpaService service;
	
	public JpaController(JpaService service) {
		this.service=service;
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/basicTest.do")
	public String basicTest() {
		service.basictest();
		return "redirect:/";
	}
	
	@GetMapping("/manytoone.do")
	public String manytoone() {
		service.manytoone();
		return "redirect:/";
	}
	
	@GetMapping("/onetoone.do")
	public String onetoone() {
		service.insertStudent();
		return "redirect:/";
	}
	
	@GetMapping("/entitydelete.do")
	public String deleteStudent(long no) {
		service.deleteStudent(no);
		return "redirect:/";
	}
	
	@PostMapping("/updatestudent.do")
	public String updateStudent(@RequestParam Map<String,Object> param) {
		
		service.updateStudent(param);
		
		return "redirect:/";
	}
	@GetMapping("/insertClub.do")
	public String insertClub() {
		service.insertClub();
		
		return "redirect:/";
	}
}
