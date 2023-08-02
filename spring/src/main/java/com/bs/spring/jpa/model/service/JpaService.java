package com.bs.spring.jpa.model.service;

import java.util.Map;

public interface JpaService {
	
	void basictest();
	
	void manytoone();
	
	void insertStudent();

	void deleteStudent(long no);
	
	void updateStudent(Map<String,Object> param);
	
	void insertClub();

}
