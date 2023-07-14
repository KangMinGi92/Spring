package com.bs.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember() {
		return "member/enrollMember"; 
	}
	
	//@RequestMapping(value="/member/insertMember.do",method=RequestMethod.POST)
	@PostMapping("/insertMember.do")
	public String insertMember(Member m, Model model) {
		
		//패스워드를 암호화해서 처리하자.
		String oriPassword=m.getPassword();
		//System.out.println(oriPassword);
		log.debug(oriPassword);
		String encodePassword=passwordEncoder.encode(oriPassword);
		//System.out.println(encodePassword);
		log.debug(encodePassword);
		m.setPassword(encodePassword);
		
		int result=service.insertMember(m);
		System.out.println(result);
		String msg,loc;
		if(result>0) {
			msg="회원가입성공";
			loc="/";
		}else {
			msg="회원가입실패";
			loc="/member/enrollMember.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		//return "redirect:/";
		return "common/msg";
	}
	
	@PostMapping("/login.do")
	public String login(@RequestParam Map param, Model model/* ,HttpSession session */) {
		System.out.println(param);
		Member m=service.selectMemberById(param);
		//암호화된값을 비교하기 위해서는 BCrptPasswordEncoder가 제공하는 메소드를 이용해야한다.
		//passwordEncoder.matches(파라미터로 전달된값,DB에 저장되있는 암호화된값);
		if(m!=null&&passwordEncoder.matches((String)param.get("password"),m.getPassword())){
		//m.getPassword().equals(param.get("password"))) {
			//로그인 성공
			//session.setAttribute("loginMember", m);
			//Model을 이용한 로그인처리하기
			model.addAttribute("loginMember", m);
		}else {
			model.addAttribute("msg","로그인 실패");
			model.addAttribute("loc","/");
			return "common/msg";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
//	public String logout(HttpSession session) {
//	if(session!=null)
//		session.invalidate();
//	return "redirect:/";
//}
	//@SessionAttribute로 등록된 내용삭제하기
	//SessionStatus객체를 이용해서 삭제
	public String logout(SessionStatus status) {
		if(!status.isComplete()) status.setComplete();
		return "redirect:/";
	}
	@RequestMapping("/mypage.do")
//	public String mypage(String userId,Model m) {
//		m.addAttribute("member", service.selectMemberById(Map.of("userId",userId)));
//		return "member/mypage";
//	}
	public String mypage() {
		return "member/mypage";
	}
	@PostMapping("/update.do")
	public String updateMember(Member m,Model model) {
		int result=service.updateMember(m);
		Map map=new HashMap();
//		Map.of("userId",m.getUserId());
		map.put("userId", m.getUserId());
		Member m2=service.selectMemberById(map);
		if(result>0) {
			model.addAttribute("loginMember", m2);
			model.addAttribute("msg","정보변경되었습니다.");
			model.addAttribute("loc","/member/mypage.do");
		}
		else {
			model.addAttribute("msg","정보수정실패하셨습니다.");
			model.addAttribute("loc","/");
		}
		return "common/msg";
	}
}
