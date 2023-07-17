package com.bs.spring.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bs.spring.board.dao.BoardDao;
import com.bs.spring.board.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {
	
	BoardDao dao;
	
	private SqlSession session;
	
	public BoardServiceImpl(BoardDao dao, SqlSessionTemplate session) {
		this.dao = dao;
		this.session = session;
	}
	
	@Override
	public List<Board> selectBoardAll(Map<String,Object> param){
		return dao.selectBoardAll(session,param);
	}
	@Override
	public int selectBoardAllCount() {
		return dao.selectBoardAllCount(session);
	}
	@Override
	public Board selectBoardByNo(int no) {
		return dao.selectBoardByNo(session,no);
	}
	@Override
	public int insertBoard(Board b) {
		return dao.insertBoard(session,b);
	}
}
