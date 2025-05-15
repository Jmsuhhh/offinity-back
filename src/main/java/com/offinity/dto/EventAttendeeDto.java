package com.offinity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventAttendeeDto {
	 	private Long id;
	    private String targetType;    
	    private String targetValue;  
	    private Long eventId;
	
}
