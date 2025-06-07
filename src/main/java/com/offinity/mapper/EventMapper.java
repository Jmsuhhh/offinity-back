package com.offinity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.offinity.dto.EventDto;

@Mapper
public interface EventMapper {
    EventDto selectEventById(Long id);
    void insertEvent(EventDto event);
    List<EventDto> selectAllEvents();
    void deleteEvent(Long id);

}
