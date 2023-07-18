package com.bs.spring.board.service;

import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.dao.BoardDao;
import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
	//@Transactional
	public int insertBoard(Board b) {
		
		//2개의 insert문을 실행!
		log.info("실행전 : {}",b.getBoardNo());
		int result=dao.insertBoard(session,b);
		log.info("실행후 : {}",b.getBoardNo());
		if(result>0) {
			if(b.getFile().size()>0) {
				for(Attachment a: b.getFile()) {
					a.setBoardNo(b.getBoardNo());
					result+=dao.insertAttachment(session,a);
					//result=dao.insertAttachment(session,a);
					//첨부파일이 이상해서 등록하지 못하면 rollback시키기 위해서 runtimeexception을 발생시켜주는구문
					//if(result!=1) throw new RuntimeException("첨부파일의 내용이 이상합니다.");
				}
			}
		}
		//rollback처리를 원한다면.... RuntimeException을 발생시키면됨
		//if(result>0) throw new RuntimeException("내가 그냥 싫어!!!!");
		if(result!=b.getFile().size()+1) throw new RuntimeException("첨부파일의 내용이 이상합니다.");
		//if(result!=0) throw new RuntimeException("첨부파일의 내용이 이상합니다.");
		return result;
		
	}
}
