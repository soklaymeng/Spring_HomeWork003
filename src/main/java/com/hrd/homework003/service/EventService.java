package com.hrd.homework003.service;

import com.hrd.homework003.model.Events;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface EventService {
    List<Events> getAllEvents();


    Events getAllEventById(Integer id) throws NotFoundException;
}
