package com.bs.spring.jpa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class StudentEntity {
	@Id
	private long studentNo;
	
	private String studentName;
	
	private int grade;
	
	private int classNumber;
	
	
	//영속성전이
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="mylocker")//, nullable = false) // nullable = false로 주면 NOT NULL 제약조건을 준거랑 마찬가지
	//기본적으로 JPA는 조인문을 사용할때 LEFT OUTER JOIN 을 사용하지만, 위처럼 NULL값을 허용하지 않으면 INNER JOIN 을 사용하요 조금 더 최적화 하여 데이터를 가져온다.
	private LockerEntity mylocker;
	
	//다대다 관계를 설정했을 때
	//Join테이블이 생성되어야한다. - > join테이블 생성에 대한 설정을 할 수 있다.
//	@ManyToMany(cascade = {CascadeType.PERSIST})
//	@JoinTable(name="student_clubs",
//	joinColumns =@JoinColumn(name="student_no") , //현재 Entity의 pk가 저장되는 컬럼에 대한 설정
//	inverseJoinColumns =@JoinColumn(name="club_no")) //상대방 Entity의 pk가 저장되는 컬럼에 대한 설정
//	private List<Club> clubs;
	
	//위 방식으로 했을때는 조인테이블을 생성해줘서 사용하면 되지만 조인테이블에 새로운 컬럼을 추가할 수 없는 치명적 단점이 있다.!!
	
	@OneToMany(mappedBy = "student")
	private List<StudentClubs> clubs;
}
