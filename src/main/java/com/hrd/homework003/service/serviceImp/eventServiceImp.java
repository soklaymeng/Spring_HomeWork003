package com.hrd.homework003.service.serviceImp;

import com.hrd.homework003.model.Events;
import com.hrd.homework003.repository.EventsRepository;
import com.hrd.homework003.service.EventService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class eventServiceImp implements EventService {
    private final EventsRepository eventsRepository;

    public eventServiceImp(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<Events> getAllEvents() {
        return eventsRepository.getAllEvent();
    }

    @Override
    public Events getAllEventById(Integer id) throws NotFoundException {
        Events events= eventsRepository.getAllEventById(id);
        if (events==null){
            throw new NotFoundException("Attendee with ID " + id + " not found");
        }
        return events;
    }


}
