package com.offinity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.offinity.dto.EventDto;
import com.offinity.mapper.EventMapper;

@Service
public class EventService {

	private EventMapper eventMapper;

	public EventService(EventMapper eventMapper) {
		this.eventMapper = eventMapper;
	}

	public List<EventDto> getAllEvents() {
	    return eventMapper.selectAllEvents();
	}

	public EventDto getEventById(Long id) {
		return eventMapper.selectEventById(id);
	}

	public void createEvent(EventDto event) {
		System.out.println("check");
		eventMapper.insertEvent(event);
	}
	public void deleteEvent(Long id) {
	    eventMapper.deleteEvent(id);
	}
}
