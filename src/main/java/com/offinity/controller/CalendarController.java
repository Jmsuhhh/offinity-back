package com.offinity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offinity.dto.CustomUserDetails;
import com.offinity.dto.EventDto;
import com.offinity.dto.HolidayDto;
import com.offinity.service.EventService;
import com.offinity.service.HolidayService;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    private final EventService eventService;
    private final HolidayService holidayService;

    public CalendarController(EventService eventService, HolidayService holidayService) {
        this.eventService = eventService;
        this.holidayService = holidayService;
    }

    @GetMapping("/summary")
    public Map<String, Object> getCalendarSummary(
            @RequestParam("year") int year,
            @RequestParam("month") int month,
            @AuthenticationPrincipal CustomUserDetails user
            ) throws Exception {

        List<HolidayDto> holidays = holidayService.getHolidays(year, month); 
//        List<EventDto> events = eventService.getAllEvents(); 
        List<EventDto> events = null;
        System.out.println("user : " + user);
        if (user != null) { 
        	events = eventService.getAllEvents();
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("events", events);
        response.put("holidays", holidays);

        return response;
    }
}