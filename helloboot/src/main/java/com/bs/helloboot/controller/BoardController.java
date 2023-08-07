package com.bs.helloboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.helloboot.dto.BoardEntity;
import com.bs.helloboot.service.JpaService;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	private JpaService service;
	
	public BoardController(JpaService service) {
		this.service=service;
	}
	
	@GetMapping
	public List<BoardEntity> selectAllBoard(){
		return service.selectBoardAll();
	}
	
	@GetMapping(params="title")
	public List<BoardEntity> selectBoardByTitle(String title){
		return service.selectByTitle(title);
	}
}
