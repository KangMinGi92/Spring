package com.bs.helloboot.dto;

import java.util.Date;

import javax.persistence.Column;
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
@Entity
@Table(name="board")
@SequenceGenerator(name="seq_boardno",sequenceName = "seq_boardno", allocationSize = 1)
public class BoardEntity {
	
	@Id
	@GeneratedValue(generator = "seq_boardno",strategy = GenerationType.SEQUENCE)
	@Column(name="boardno")
	private long boardNo;
	
	@Column(name="boardtitle")
	private String boardTitle;
	
	@Column(name="boardwriter")
	private String boardWriter;
	
	@Column(name="boardcontent")
	private String boardContent;
	
	@Column(name="boarddate")
	private Date boardDate;
	
	@Column(name="boardreadcount")
	private String boardReadCount;
	
	
}
