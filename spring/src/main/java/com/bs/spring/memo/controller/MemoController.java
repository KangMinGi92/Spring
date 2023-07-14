package com.bs.spring.memo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bs.spring.memo.model.dto.Memo;
import com.bs.spring.memo.service.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {
	
	private MemoService service;
//	@Autowired
//	MemoService service;
	
	//@Autowired spring 4버전 이상부터는 생략해도 알아서 매칭해줌
	public MemoController(MemoService service) {
		this.service=service;
	}
	@RequestMapping("/selectMemoAll.do")
	public String selectMemoAll(Model model) {
		List<Memo> list=service.selectMemoAll();
		model.addAttribute("memos",list);
		return "memo/memoList";
	}
	
	@RequestMapping(value="/insertmemo.do", method=RequestMethod.POST)
	public String insertMemo(Memo m,Model model) {
		int result=service.insertMemo(m);
		if(result>0) {
			model.addAttribute("msg","메모등록성공");
			model.addAttribute("loc","/memo/selectMemoAll.do");
		}else {
			model.addAttribute("msg","메모등록실패");
			model.addAttribute("loc","/memo/selectMemoAll.do");
		}
		return "common/msg";
	}
	
}
