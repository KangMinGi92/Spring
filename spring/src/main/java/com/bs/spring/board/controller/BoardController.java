package com.bs.spring.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.service.BoardServiceImpl;
import com.bs.spring.common.PageFactory;

@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardServiceImpl service;
	
	public BoardController(BoardServiceImpl service) {
		this.service=service;
	}
	
	@RequestMapping("/boardList.do")
	public String selectMemoAll(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="5") int numPerpage,
			Model m,HttpServletRequest request) {
		//페이지에 맞는 데이터를 가져와야함.
		List<Board> boards=service.selectBoardAll(Map.of("cPage",cPage,"numPerpage",numPerpage));
		int totalData=service.selectBoardAllCount();
		//paging
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData,"boardList.do"));
		m.addAttribute("totalContents",totalData);
		m.addAttribute("boards",boards);
		return "board/boardList";
	}
	@RequestMapping("/insertBoardpage.do")
	public String insertBoardpage(Board b){
		return "board/enrollBoard";
	}
	
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(Board b,Model m) {
		int result=service.insertBoard(b);
		if(result>0) {
			m.addAttribute("msg","게시글등록성공");
			m.addAttribute("loc","/board/boardList.do");
		}else {
			m.addAttribute("msg","게시글등록실패");
			m.addAttribute("loc","/board/insertBoardpage.do");
		}
		
		return "common/msg";
	}
	
	@RequestMapping("/boardView.do")
	public String selectBoardByNo(Model m,int no){
		m.addAttribute("board",service.selectBoardByNo(no));
		return "board/boardView";
	}
}
