package com.bs.spring.jpa.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.bs.spring.jpa.common.Level;
import com.bs.spring.jpa.common.Role;

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
@Table//(name="memberjpa")
@SequenceGenerator(name="seq_jpamemberno",sequenceName="seq_jpamemberno",initialValue = 1,allocationSize = 1 )
public class JpaMember {
	
	@Id //Entity를 식별하는 식별자, DB에서는 Primarykey제약조건이 설정
	@GeneratedValue(generator = "seq_jpamemberno",strategy = GenerationType.SEQUENCE)
	@Column(name = "member_no")
	private Long memberNo;
	
	@Column(name = "member_Id", unique = true, nullable = false, length = 20)//String 일경우 아무설정안하면 default : varchar2(255)설정됨
	private String memberId;
	
	@Column(name = "member_pwd", nullable = false, length = 20)
	private String memberPwd;
	
	@Column(precision = 10 , scale=3)
	private BigDecimal age;
	
	@Column(columnDefinition = "number default 100.0")
	private double height;
	
	
	//EnumType을 이용해서 처리하기
	@Column(name="member_role")
	@Enumerated(EnumType.STRING) //문자열자체를 저장(EnumClass의 데이터에 선언되있는 문자열 자체를 DB에 저장)
	private Role role;
	
	@Column(name="member_level")
	@Enumerated(EnumType.ORDINAL) //문자열과 연결되어있는 숫자값(왼쪽부터 0부터 시작해서 매핑해서 숫자를 DB에 저장(EnumClass를 수정하면 데이터가 틀어지기때문에 비추천)) 
	private Level level;
	
	//날짜타입에 대해 설정하기
	@Temporal(TemporalType.DATE)
	private Date birthDay;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	
	//lob타입설정하기
	@Lob
	private String info;
	
	@Lob
	private byte[] dataSample;
	
	//DB컬럼 대상에서 제외하기
	@Transient
	private String tempData;
	
	@Embedded
	private Address addr;
	
	@Transient
	private String target;
	
	//서브클래스는 mappedBy 속성을 추가한다. boardEntity에는 이미 fk값으로 memberno컬럼을 갖고있으므로 따로 조인테이블을 만들필요가 없어 속성을 추가한다.
	//List타입을 필드로 갖는 곳에 설정하자.(즉 fk을 주는쪽 필드에 mappedBy속성을 선언한다.)
	//지연로딩처리하기 -> 프록시객체를 먼저불러오고 실제 데이터를 사용할때 가져오게 만드는 기술?
	@OneToMany(mappedBy = "boardWriter")//fetch =FetchType.EAGER //fetch = FetchType.LAZY (Default LAZY로 설정되어있음)
	List<BoardEntity> boards;
	
	
	
}
