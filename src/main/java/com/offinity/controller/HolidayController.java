package com.offinity.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offinity.dto.HolidayDto;
import com.offinity.service.HolidayService;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping
    public List<HolidayDto> getHolidays(
        @RequestParam("year") int year,
        @RequestParam("month") int month
    ) throws Exception {
        return holidayService.getHolidays(year, month);
    }
}
