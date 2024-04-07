package com.hrd.homework003.service.serviceImp;

import com.hrd.homework003.exception.VenueNotFoundException;
import com.hrd.homework003.model.Attendees;
import com.hrd.homework003.model.dto.request.AttendeeRequest;
import com.hrd.homework003.repository.AttendeeRepository;
import com.hrd.homework003.service.AttendeeService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImp implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImp(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    // Find all attendees
    @Override
    public List<Attendees> getAllAttendees(Integer page, Integer size) {
        return attendeeRepository.getAllAttendees(page, size);
    }

    // Find attendee by ID
    @Override
    public Attendees findAttendeesById(Integer id) {
        Attendees attendee = attendeeRepository.findAttendeesById(id);
        if (attendee == null) {
            throw new VenueNotFoundException("Attendee with ID " + id + " not found");
        }
        return attendee;
    }
    // Insert new attendee
    @Override
    public Attendees insertAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.insertAttendee(attendeeRequest);
    }

    @Override
    public Attendees updateAttendeeById(Integer id, AttendeeRequest attendeeRequest) {
        if (attendeeRepository.findAttendeesById(id)==null){
            throw new VenueNotFoundException("Could not be update with id " + id + " , because it doesn't exist");
        }
        return attendeeRepository.updateAttendeeById(id, attendeeRequest);
    }

    @Override
    public void deleteAttendeeById(Integer id) {
        if (attendeeRepository.findAttendeesById(id)==null){
            throw new VenueNotFoundException("Could not delete attendee with id " +id+ "  , because it doesn't exist");
        }
        attendeeRepository.deleteAttendeeById(id);
    }
}
