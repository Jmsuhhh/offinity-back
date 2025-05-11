package com.offinity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDto {
	private Long eventId;            
    private String title;            
    private String description;      
    private LocalDate startDate;     
    private LocalDate endDate;       
    private LocalTime startTime;     
    private LocalTime endTime;       
    private Boolean isAllDay;        
    private String type;             
    private String location;         
    private Long createdBy;          
    private LocalDateTime createdAt; 
    private LocalDateTime updatedAt; 

    private List<EventAttendee> attendees;  
}
