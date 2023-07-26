package com.bs.spring.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//일반 pojo클래스를 Entity로 등록하기 위해서는
//@Entity를 이용한다. -> 클래스 선언부에 선언.
//기본생성자는 필수로 있어야 함. final클래스(enum, interface, inner)는 사용불가, 필드에 final 예약어 사용불가
@Entity //(name = "")별도의 이름을 부여하고싶으면 속성값 name=""에 넣어주면되고 default로 아무설정 안해주면 class명이 Entity명 이된다.
//@Table이용하기 -> Table에 대한 설정을 하는 어노테이션
//(schema, catalog oracle이외의db프레임웍의 설정값),테이블레벨 제약조건설정(ex. unique같은 제약조건) 
//@Table//(name = "") DB에 생성될 테이블명 설정(생략하면 default로 class명이 Table명 이된다.)
//@SequenceGenerator -> DB에서 사용할 sequence를 생성하는 어노테이션, ID값을 자동부여할때 사용
//속성 종류 name="엔티티에등록할 시퀀스이름",sequenceName="DB에등록할 시퀀스이름",initialValue=시작값,allocationSize=증가값
//@TableGenerator -> ID값을 중복없이 발급하는 테이블을 생성해서 ID를 부여하는 용도

//@JsonIdentityInfo - > Entity객체를 가져올 때 양방향으로 일대다, 다대일 관계에 있으면 
//						무한루핑현상이 발생하는데 이를 차단하는 어노테이션
@Table(name="memberjpa")
@SequenceGenerator(name="seq_jpamemberno",sequenceName="seq_jpamemberno",initialValue = 1,allocationSize = 1 )
public class JpaMember {
	
	@Id //Entity를 식별하는 식별자, DB에서는 Primarykey제약조건이 설정
	@GeneratedValue(generator = "seq_jpamemberno",strategy = GenerationType.SEQUENCE)
	private Long memberNo;
	
	private String memberId;
	private String memberPwd;
	private Integer age;
	private double height;
}
