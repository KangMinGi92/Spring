package com.bs.spring.memo.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.dao.MemoDao;
import com.bs.spring.memo.model.dto.Memo;

@Service
public class MemoServiceImpl implements MemoService {
	
//	@Autowired
	MemoDao dao;
	
//	@Autowired
	SqlSessionTemplate session;
	
	public MemoServiceImpl(MemoDao dao, SqlSessionTemplate session) {
		this.dao = dao;
		this.session = session;
	}

	@Override
	public int insertMemo(Memo m) {
		return dao.insertMemo(session,m);
	}
	
	@Override
	public List<Memo> selectMemoAll(){
		return dao.selectMemoAll(session);
	}
}
