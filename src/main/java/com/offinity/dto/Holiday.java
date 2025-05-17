package com.offinity.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Holiday {

	private String dateName; 
	private LocalDate date; 
	private Boolean isHoliday;
	private String week; 
	private Integer weekday;

}
