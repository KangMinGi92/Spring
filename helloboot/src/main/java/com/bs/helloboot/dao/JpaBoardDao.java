package com.bs.helloboot.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bs.helloboot.dto.BoardEntity;

@Repository
public interface JpaBoardDao extends JpaRepository<BoardEntity,Long>,PagingAndSortingRepository<BoardEntity,Long> {
	
	//필요한 컬럼에 대한 조회 메소드를 형식 맞춰서 추상메소드로 만들어 준다.
	//select -> findBy필드명(String value) -> 필드와 일치하는 값을 조회 where 필드명 = 매개변수값
	// 부분일치 -> findBy필드명Like(값) -> where 필드명 like '%'||값||'%';
	// 다수비교하기 -> findBy필드명(And||or)필드명2(값,값2) -> where 필드명 = 값 and 필드명2 = 값2
	// null값 확인 -> findBy필드명IsNull || IsNotNull() -> 
	// 정렬 -> findBy필드명OrderBy필드명Asc || Desc(value)
	List<BoardEntity> findByBoardTitleLike(String title);
	
	List<BoardEntity> findByBoardWriter(String boardWriter);
	
	//페이징처리하기
	Page<BoardEntity> findAll(Pageable p);	
	
}
