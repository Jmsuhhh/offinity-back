package com.offinity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventAttendee {
	    private Long id;
	    private String targetType;   // 사원, 부서, 전체 
	    private String targetValue;  // 사원명, 부서명  
	    private Long eventId;
}
