package com.bs.spring.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"student"})
@Entity
public class LockerEntity {
	@Id
	private long lockerNo;
	private String lockerPosition;
	private String lockerColor;
	
	@OneToOne(mappedBy = "mylocker", cascade = CascadeType.REMOVE)//cascadeType.REMOVE부모테이블의 데이터를 지우면 fk로 참조하고있는 자식테이블의 데이터도 같이 날라가게 해준다.
	private StudentEntity student;
}
