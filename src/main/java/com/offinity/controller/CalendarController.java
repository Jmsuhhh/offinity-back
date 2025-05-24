package com.offinity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam("month") int month) throws Exception {

        List<EventDto> events = eventService.getAllEvents(); 
        List<HolidayDto> holidays = holidayService.getHolidays(year, month); 

        Map<String, Object> response = new HashMap<>();
        response.put("events", events);
        response.put("holidays", holidays);

        return response;
    }
}