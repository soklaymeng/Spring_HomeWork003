package com.hrd.homework003.service;

import com.hrd.homework003.model.Events;

import com.hrd.homework003.model.dto.request.EventRequest;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface EventService {
    List<Events> getAllEvents();


    Events getAllEventById(Integer id) throws NotFoundException;


    Events InsertEvents(EventRequest eventRequest);
}
