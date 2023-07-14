package com.bs.spring.memo.service;

import java.util.List;

import com.bs.spring.memo.model.dto.Memo;

public interface MemoService {
	
	int insertMemo(Memo m);
	
	List<Memo> selectMemoAll();
}
