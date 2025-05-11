package com.offinity.service;

import org.springframework.stereotype.Service;

import com.offinity.dto.EventDto;
import com.offinity.mapper.EventMapper;

@Service
public class EventService {

	private EventMapper eventMapper;
	
	public EventDto getEventById(Long id) {
		return eventMapper.selectEventById(id);
	}
	
	public void createEvent(EventDto event) {
		eventMapper.insertEvent(event);
	}
}
