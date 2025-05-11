package com.offinity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offinity.dto.EventDto;
import com.offinity.service.EventService;

// Callendar 표시할 이벤트를 저장/수정/삭제/조회하는 컨트롤러 담당자 : 이희정 
@RestController
@RequestMapping("/events")
public class EventController {
	private final EventService eventService;

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping("/{id}")
	public EventDto getEventById(@PathVariable Long id) {
		return eventService.getEventById(id);
	}

	@PostMapping
	public void createEvent(@RequestBody EventDto eventDto) {
		eventService.createEvent(eventDto);
	}
}
