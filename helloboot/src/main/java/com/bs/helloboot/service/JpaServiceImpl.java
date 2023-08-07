package com.bs.helloboot.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bs.helloboot.dao.JpaBoardDao;
import com.bs.helloboot.dao.JpaMemberDao;
import com.bs.helloboot.dto.BoardEntity;
import com.bs.helloboot.dto.MemberDto;

@Service
public class JpaServiceImpl implements JpaService {

	private JpaMemberDao memberDao;
	private JpaBoardDao boardDao;
	
	public JpaServiceImpl(JpaMemberDao memberdao,JpaBoardDao boardDao) {
		this.memberDao=memberdao;
		this.boardDao=boardDao;
	}
	
	@Override
	public MemberDto selectById(String id) {
		return memberDao.findById(id).orElseThrow();
	}

	@Override
	public void insertMember(MemberDto m) {
		memberDao.save(m);
	}

	@Override
	public List<BoardEntity> selectBoardAll() {
		// TODO Auto-generated method stub
		//return boardDao.findAll();
		return boardDao.findAll(PageRequest.of(0, 5,Sort.by("boardDate").descending())).get().toList();
	}

	@Override
	public List<BoardEntity> selectByTitle(String title) {
		return boardDao.findByBoardTitleLike("%"+title+"%");
	}

	@Override
	public List<BoardEntity> selectByTitleAndContent(String title, String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void boardRemove(long boardNo) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
