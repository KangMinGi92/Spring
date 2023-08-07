package com.bs.spring.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="board")
@ToString(exclude = "boardWriter")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebBoard {
	
	@Id
	@Column(name="board_no")
	private int boardNo;
	
	@Column(name="board_title")
	private String boardTitle;
	@ManyToOne
	@JoinColumn(name="board_writer")
	private WebMember boardWriter;
	
	@Column(name="board_content")
	private String boardContent;
	@Column(name="board_original_filename")
	private String boardOriginalFilename;
	@Column(name="board_renamed_filename")
	private String boardRenamedFilename;
	
	@Column(name="board_date")
	private Date boardDate;
	
	@Column(name="board_readcount")
	private int boardRedcount;
	
}
