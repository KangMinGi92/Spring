package com.bs.spring.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Club {
	@Id
	private long clubNo;
	
	private String clubName;
	private String location;
	
//	@ManyToMany(mappedBy = "clubs")
//	private List<StudentEntity> students;
	
	@OneToMany(mappedBy = "club")
	private List<StudentClubs> students;
}
