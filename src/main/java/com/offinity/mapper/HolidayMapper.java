package com.offinity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.offinity.dto.Holiday;

@Mapper
public interface HolidayMapper {
	void insertHoliday(Holiday holiday);

	void insertHolidays(List<Holiday> holidays);

	List<Holiday> selectAllHolidays();
}
