package com.hrd.homework003.service;

import com.hrd.homework003.model.Events;

import com.hrd.homework003.model.dto.request.EventRequest;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface EventService {
    List<Events> getAllEvents(Integer page, Integer size);


    Events getAllEventById(Integer id);


    Events InsertEvents(EventRequest eventRequest);

    Events updateEventById(Integer id, EventRequest eventRequest);

    Events deleteEventById(Integer id);
}
