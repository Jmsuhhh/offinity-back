package com.offinity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
	private Long eventId;                // 일정 고유 ID
    private String title;                // 일정 제목
    private String description;          // 일정 상세 설명
    private LocalDate startDate;         // 시작 날짜
    private LocalDate endDate;           // 종료 날짜
    private LocalTime startTime;         // 시작 시간
    private LocalTime endTime;           // 종료 시간
    private Boolean isAllDay;            // 종일 여부
    private String type;                 // 일정 타입
    private String location;             // 장소
    private Long createdBy;              // 작성자 ID
    private LocalDateTime createdAt;     // 생성 일시
    private LocalDateTime updatedAt;	 // 수정 일시 

    private List<EventAttendee> attendees; //참석자 목록 
}
