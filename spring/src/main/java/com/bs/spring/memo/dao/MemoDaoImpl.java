package com.bs.spring.memo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.memo.model.dto.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {
	
	@Override
	public int insertMemo(SqlSessionTemplate session, Memo m) {
		return session.insert("memo.insertmemo", m);
	}
	
	@Override
	public List<Memo> selectMemoAll(SqlSessionTemplate session){
		return session.selectList("memo.selectMemoAll");
	}
}
