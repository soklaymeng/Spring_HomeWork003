package com.hrd.homework003.service.serviceImp;

import com.hrd.homework003.model.Events;
import com.hrd.homework003.model.dto.request.EventRequest;
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
    public List<Events> getAllEvents(Integer page, Integer size) {

        return eventsRepository.getAllEvent(page,size);
    }

    @Override
    public Events getAllEventById(Integer id) throws NotFoundException {
        Events events= eventsRepository.getAllEventById(id);
        if (events==null){
            throw new NotFoundException("Attendee with ID " + id + " not found");
        }
        return events;
    }

    @Override
    public Events InsertEvents(EventRequest eventRequest) {

        Events eventId = eventsRepository.InsertEvents(eventRequest);
        for (Integer attendeeId : eventRequest.getAttendeeId()) {
            System.out.println(" show attendeeiD"+attendeeId);
            eventsRepository.insertIntoEventAttendee(eventId.getEventId(), attendeeId);

        }
        return eventsRepository.findEventById(eventId.getEventId());
    }

    @Override
    public Events updateEventById(Integer id, EventRequest eventRequest) {
        return eventsRepository.updateEventById(id,eventRequest);
    }

    @Override
    public Events deleteEventById(Integer id) throws NotFoundException {
        if (eventsRepository.findEventById(id)==null){
            throw new NotFoundException("Event with ID " + id + " not found");
        }
        return eventsRepository.deleteEventById(id);
    }


}
