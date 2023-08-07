package com.bs.helloboot.service;

import java.util.List;

import com.bs.helloboot.dto.BoardEntity;
import com.bs.helloboot.dto.MemberDto;

public interface JpaService {
	MemberDto selectById(String id);
	void insertMember(MemberDto m);
	
	List<BoardEntity> selectBoardAll();
	List<BoardEntity> selectByTitle(String title);
	List<BoardEntity> selectByTitleAndContent(String title,String content);
	
	void boardRemove(long boardNo);
}
