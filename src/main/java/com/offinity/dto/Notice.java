package com.offinity.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notice { // 내부 DB와 소통 
	
	private Integer noticeId;
	private String writer;
	private String title;
	private String content;

	private LocalDateTime createDate;
	private LocalDateTime updateDate;
}
