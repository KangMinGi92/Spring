package com.bs.spring.demo.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.dao.DemoDao;
import com.bs.spring.demo.model.dto.Demo;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	private DemoDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int insertDemo(Demo demo) {
		return dao.insertDemo(session,demo);
	}

	@Override
	public List<Demo> selectDemoAll() {
		return dao.selectDemoAll(session);
	}
	
	@Override
	public int updateDemo(Long no) {
		return dao.updateDemo(session,no);
	}

}
