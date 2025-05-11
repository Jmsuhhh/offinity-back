package com.offinity.mapper;

import com.offinity.dto.EventDto;

public interface EventMapper {
    EventDto selectEventById(Long id);
    void insertEvent(EventDto event);
}
