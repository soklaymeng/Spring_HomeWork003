package com.hrd.homework003.service.serviceImp;

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
    public List<Attendees> getAllAttendees() {
        return attendeeRepository.getAllAttendees();
    }

    // Find attendee by ID
    @Override
    public Attendees findAttendeesById(Integer id) throws NotFoundException {
        Attendees attendee = attendeeRepository.findAttendeesById(id);
        if (attendee == null) {
            throw new NotFoundException("Attendee with ID " + id + " not found");
        }
        return attendee;
    }
    // Insert new attendee
    @Override
    public Attendees insertAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.insertAttendee(attendeeRequest);
    }

    @Override
    public Attendees updateAttendeeById(Integer id, AttendeeRequest attendeeRequest) throws NotFoundException {
        if (attendeeRepository.findAttendeesById(id)==null){
            throw new NotFoundException("Venue with id " + id + " not found");
        }
        return attendeeRepository.updateAttendeeById(id, attendeeRequest);
    }

    @Override
    public void deleteAttendeeById(Integer id) {
        attendeeRepository.deleteAttendeeById(id);
    }
}
